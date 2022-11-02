package com.atxin.glmall.tre;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 * �������㷨������
 */
public class TestDecisionTree {
	/**
	 * ��ȡ��ѡ����
	 * @return ��ѡ���Լ���
	 * @throws IOException
	 */
	public ArrayList<String> readCandAttr() throws IOException{
		ArrayList<String> candAttr = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		while (!(str = reader.readLine()).equals("")) {
			StringTokenizer tokenizer = new StringTokenizer(str);
			while (tokenizer.hasMoreTokens()) {
				candAttr.add(tokenizer.nextToken());
			}
		}
		return candAttr;
	}
	
	/**
	 * ��ȡѵ��Ԫ��
	 * @return ѵ��Ԫ�鼯��
	 * @throws IOException
	 */
	public ArrayList<ArrayList<String>> readData() throws IOException {
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		while (!(str = reader.readLine()).equals("")) {
			StringTokenizer tokenizer = new StringTokenizer(str);
			ArrayList<String> s = new ArrayList<String>();
			while (tokenizer.hasMoreTokens()) {
				s.add(tokenizer.nextToken());
			}
			datas.add(s);
		}
		return datas;
	}
	
	/**
	 * �ݹ��ӡ���ṹ
	 * @param root ��ǰ�������Ϣ�Ľ��
	 */
	public void printTree(TreeNode root){
		System.out.println("name:" + root.getName());
		ArrayList<String> rules = root.getRule();
		System.out.print("node rules: {");
		for (int i = 0; i < rules.size(); i++) {
			System.out.print(rules.get(i) + " ");
		}
		System.out.print("}");
		System.out.println("");
		ArrayList<TreeNode> children = root.getChild();
		int size =children.size();
		if (size == 0) {
			System.out.println("-->leaf node!<--");
		} else {
			System.out.println("size of children:" + children.size());
			for (int i = 0; i < children.size(); i++) {
				System.out.print("child " + (i + 1) + " of node " + root.getName() + ": ");
				printTree(children.get(i));
			}
		}
	}
	/**
	 * ���������������
	 * @param args
	 */
	public static void main(String[] args) {
		TestDecisionTree tdt = new TestDecisionTree();
		ArrayList<String> candAttr = null;
		ArrayList<ArrayList<String>> datas = null;
		try {
			System.out.println("�������ѡ����");
			candAttr = tdt.readCandAttr();
			System.out.println("������ѵ������");
			datas = tdt.readData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DecisionTree tree = new DecisionTree();
		TreeNode root = tree.buildTree(datas, candAttr);
		tdt.printTree(root);
	}
}

