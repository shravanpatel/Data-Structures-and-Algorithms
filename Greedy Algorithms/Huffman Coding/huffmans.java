import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class huffmans {
	private static Map<Character, String> charPrefixHashMap = new HashMap<>();
	private static HuffmanNode root;

	private static class HuffmanNode implements Comparable<HuffmanNode> {
		char data;
		int frequency;
		HuffmanNode left, right;

		public int compareTo(HuffmanNode node) {
			if (frequency < node.frequency) {
				return -1;
			} else if (frequency > node.frequency) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	private static HuffmanNode buildTree(Map<Character, Integer> frequencyCount) {
		PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
		Set<Character> keySet = frequencyCount.keySet();

		for (Character c : keySet) {
			HuffmanNode huffmanNode = new HuffmanNode();
			huffmanNode.data = c;
			huffmanNode.frequency = frequencyCount.get(c);
			huffmanNode.left = null;
			huffmanNode.right = null;
			priorityQueue.offer(huffmanNode);
		}

		while (priorityQueue.size() > 1) {
			HuffmanNode last = priorityQueue.peek();
			priorityQueue.poll();

			HuffmanNode secondLast = priorityQueue.peek();
			priorityQueue.poll();

			HuffmanNode sum = new HuffmanNode();
			sum.frequency = last.frequency + secondLast.frequency;
			sum.data = '-';
			sum.left = last;
			sum.right = secondLast;
			root = sum;

			priorityQueue.offer(sum);
		}
		return priorityQueue.poll();
	}

	private static void setPrefixCodes(HuffmanNode node, StringBuilder prefix) {
		if (node != null) {
			if (node.left == null && node.right == null) {
				charPrefixHashMap.put(node.data, prefix.toString());
			} else {
				prefix.append('0');
				setPrefixCodes(node.left, prefix);
				prefix.deleteCharAt(prefix.length() - 1);

				prefix.append('1');
				setPrefixCodes(node.right, prefix);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		}
	}

	private static String encode(String original) {
		Map<Character, Integer> frequencyCount = new HashMap<>();
		for (int i = 0; i < original.length(); i++) {
			if (!frequencyCount.containsKey(original.charAt(i))) {
				frequencyCount.put(original.charAt(i), 0);
			}
			frequencyCount.put(original.charAt(i), frequencyCount.get(original.charAt(i)) + 1);
		}

		HashMap<Character, Integer> frequencyMapSortedDesc = frequencyCount.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (c1, c2) -> c1, LinkedHashMap::new));

		System.out.println("Character Frequency Map: " + frequencyMapSortedDesc);
		root = buildTree(frequencyCount);

		setPrefixCodes(root, new StringBuilder());
		System.out.println("Character Prefix Map: " + charPrefixHashMap);

		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < original.length(); i++) {
			char c = original.charAt(i);
			stringBuilder.append(charPrefixHashMap.get(c));
		}
		return stringBuilder.toString();
	}

	private static String decode(String converted) {
		StringBuilder stringBuilder = new StringBuilder();
		HuffmanNode temp = root;

		for (int i = 0; i < converted.length(); i++) {
			int j = Integer.parseInt(String.valueOf(converted.charAt(i)));
			if (j == 0) {
				temp = temp.left;
				if (temp.left == null && temp.right == null) {
					stringBuilder.append(temp.data);
					temp = root;
				}
			}
			if (j == 1) {
				temp = temp.right;
				if (temp.left == null && temp.right == null) {
					stringBuilder.append(temp.data);
					temp = root;
				}
			}
		}
		return stringBuilder.toString();
	}

	private static class GUI implements ActionListener {
		private static JFrame frame;
		private static JPanel panel;
		private static JLabel textLabel;
		private static JTextArea textarea;
		private static JButton encodeButton;
		private static JLabel encodedLabel;
		private static JTextArea encodedTextarea;
		private static JButton decodeButton;
		private static JLabel decodedLabel;
		private static JTextArea decodedTextarea;
		private static JButton clearButton; 

		public GUI() {
			frame = new JFrame("Encoding Demostration using Huffmans Coding");
			frame.setSize(475, 455);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			panel = new JPanel();
			panel.setLayout(null);
			frame.add(panel);

			textLabel = new JLabel("Text for encoding: ");
			textLabel.setBounds(50, 30, 120, 20);
			textLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(textLabel);

			textarea = new JTextArea();
			textarea.setBounds(175, 30, 250, 65);
			textarea.setLineWrap(true);
			panel.add(textarea);

			encodeButton = new JButton("Encode");
			encodeButton.setBounds(175, 105, 75, 20);
			encodeButton.addActionListener(this);
			panel.add(encodeButton);

			encodedLabel = new JLabel("Encoded Text: ");
			encodedLabel.setBounds(50, 135, 120, 20);
			encodedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(encodedLabel);

			encodedTextarea = new JTextArea();
			encodedTextarea.setBounds(175, 135, 250, 130);
			encodedTextarea.setLineWrap(true);
			encodedTextarea.setEditable(false);
			panel.add(encodedTextarea);

			decodeButton = new JButton("Decode");
			decodeButton.setBounds(175, 275, 75, 20);
			decodeButton.addActionListener(this);
			panel.add(decodeButton);

			decodedLabel = new JLabel("Decoded Text: ");
			decodedLabel.setBounds(50, 305, 120, 20);
			decodedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(decodedLabel);

			decodedTextarea = new JTextArea();
			decodedTextarea.setBounds(175, 305, 250, 65);
			decodedTextarea.setLineWrap(true);
			decodedTextarea.setEditable(false);
			panel.add(decodedTextarea);

			clearButton = new JButton("Reset");
			clearButton.setBounds(175, 380, 75, 20);
			clearButton.addActionListener(this);
			panel.add(clearButton);
		
			frame.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (textarea.getText().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Enter the text in the text box.", "Invalid",
						JOptionPane.WARNING_MESSAGE);
			} else {
				String original = textarea.getText();
				System.out.println("Original Text: " + original);
				String encoded = encode(original);
				String decoded = decode(encoded);

				if (e.getSource() == encodeButton) {
					System.out.println("Encoded Text: " + encoded);
					encodedTextarea.setText(encoded);
				}

				if (e.getSource() == decodeButton) {
					System.out.println("Decoded Text: " + decoded);
					decodedTextarea.setText(decoded);
				}
				System.out.println();

				if (e.getSource() == clearButton) {
					textarea.setText("");
					encodedTextarea.setText("");
					decodedTextarea.setText("");
				}
			}
		}
	}

	public static void main(String[] args) {
		GUI main = new GUI();
	}
}