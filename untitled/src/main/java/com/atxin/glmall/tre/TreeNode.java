package com.atxin.glmall.tre;
import java.util.ArrayList;
/**
 * �����������
 */
public class TreeNode {
	private String name; //�ڵ������������Ե����ƣ�
	private ArrayList<String> rule; //���ķ��ѹ���
	ArrayList<TreeNode> child; //�ӽ�㼯��
	private ArrayList<ArrayList<String>> datas; //���ֵ��ý���ѵ��Ԫ��
	private ArrayList<String> candAttr; //���ֵ��ý��ĺ�ѡ����
	public TreeNode() {
		this.name = "";
		this.rule = new ArrayList<String>();
		this.child = new ArrayList<TreeNode>();
		this.datas = null;
		this.candAttr = null;
	}
	public ArrayList<TreeNode> getChild() {
		return child;
	}
	public void setChild(ArrayList<TreeNode> child) {
		this.child = child;
	}
	public ArrayList<String> getRule() {
		return rule;
	}
	public void setRule(ArrayList<String> rule) {
		this.rule = rule;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<ArrayList<String>> getDatas() {
		return datas;
	}
	public void setDatas(ArrayList<ArrayList<String>> datas) {
		this.datas = datas;
	}
	public ArrayList<String> getCandAttr() {
		return candAttr;
	}
	public void setCandAttr(ArrayList<String> candAttr) {
		this.candAttr = candAttr;
	}
}

