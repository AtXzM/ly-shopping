package com.atxin.glmall.tre;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.smartcardio.*;
/**
 * ������������
 */
public class DecisionTree { 
	private Integer attrSelMode;  //��ѷ�������ѡ��ģʽ��1��ʾ����Ϣ���������2��ʾ����Ϣ�����ʶ�������δʵ��2
	public DecisionTree(){
		this.attrSelMode = 1;
	}
	
	public DecisionTree(int attrSelMode) {
		this.attrSelMode = attrSelMode;
	}
	public void setAttrSelMode(Integer attrSelMode) {
		this.attrSelMode = attrSelMode;
	}
	/**
	 * ��ȡָ�����ݼ��е���������
	 * @param datas ָ�������ݼ�
	 * @return ����������map
	 */
	public Map<String, Integer> classOfDatas(ArrayList<ArrayList<String>> datas){
		Map<String, Integer> classes = new HashMap<String, Integer>();
		String c = "";
		ArrayList<String> tuple = null;
		for (int i = 0; i < datas.size(); i++) {
			tuple = datas.get(i);
			c = tuple.get(tuple.size() - 1);
			if (classes.containsKey(c)) {
				classes.put(c, classes.get(c) + 1);
			} else {
				classes.put(c, 1);
			}
		}
		return classes;
	}
	
	/**
	 * ��ȡ���������������������������
	 * @param classes ��ļ�ֵ����
	 * @return �����������
	 */
	public String maxClass(Map<String, Integer> classes){
		String maxC = "";
		int max = -1;
		Iterator iter = classes.entrySet().iterator();
		for(int i = 0; iter.hasNext(); i++)
		{
			Map.Entry entry = (Map.Entry) iter.next(); 
			String key = (String)entry.getKey();
			Integer val = (Integer) entry.getValue(); 
			if(val > max){
				max = val;
				maxC = key;
			}
		}
		return maxC;
	}
	
	/**
	 * ���������
	 * @param datas ѵ��Ԫ�鼯��
	 * @param attrList ��ѡ���Լ���
	 * @return �����������
	 */
	public TreeNode buildTree(ArrayList<ArrayList<String>> datas, ArrayList<String> attrList){
//		System.out.print("��ѡ�����б� ");
//		for (int i = 0; i < attrList.size(); i++) {
//			System.out.print(" " + attrList.get(i) + " ");
//		}
		System.out.println();
		TreeNode node = new TreeNode();
		node.setDatas(datas);
		node.setCandAttr(attrList);
		Map<String, Integer> classes = classOfDatas(datas);
		String maxC = maxClass(classes);
		if (classes.size() == 1 || attrList.size() == 0) {
			node.setName(maxC);
			return node;
		}
		Gain gain = new Gain(datas, attrList);
		int bestAttrIndex = gain.bestGainAttrIndex();
		ArrayList<String> rules = gain.getValues(datas, bestAttrIndex);
		node.setRule(rules);
		node.setName(attrList.get(bestAttrIndex));
		if(rules.size() > 2){ //?�˴��д���ȶ
			attrList.remove(bestAttrIndex);
		}
		for (int i = 0; i < rules.size(); i++) {
			String rule = rules.get(i);
			ArrayList<ArrayList<String>> di = gain.datasOfValue(bestAttrIndex, rule);
			for (int j = 0; j < di.size(); j++) {
				di.get(j).remove(bestAttrIndex);
			}
			if (di.size() == 0) {
				TreeNode leafNode = new TreeNode();
				leafNode.setName(maxC);
				leafNode.setDatas(di);
				leafNode.setCandAttr(attrList);
				node.getChild().add(leafNode);
			} else {
				TreeNode newNode = buildTree(di, attrList);
				node.getChild().add(newNode);
			}
			
		}
		return node;
	}
}
