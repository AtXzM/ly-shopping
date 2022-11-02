package com.atxin.glmall.tre;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * ѡ����ѷ�������
 */
public class Gain {
	private ArrayList<ArrayList<String>> D = null; //ѵ��Ԫ��
	private ArrayList<String> attrList = null; //��ѡ���Լ�
	public Gain(ArrayList<ArrayList<String>> datas, ArrayList<String> attrList) {
		this.D = datas;
		this.attrList = attrList;
	}
	
	/**
	 * ��ȡ��Ѻ�ѡ�������ϵ�ֵ�򣨼ٶ������������ϵ�ֵ�������޵����ʻ�������͵ģ�
	 * @param attrIndex ָ���������е�����
	 * @return ֵ�򼯺�
	 */
	public ArrayList<String> getValues(ArrayList<ArrayList<String>> datas, int attrIndex){
		ArrayList<String> values = new ArrayList<String>();
		String r = "";
		for (int i = 0; i < datas.size(); i++) {
			r = datas.get(i).get(attrIndex);
			if (!values.contains(r)) {
				values.add(r);
			}
		}
		return values;
	}
	
	/**
	 * ��ȡָ�����ݼ���ָ����������������ֵ�������
	 * @param d ָ�������ݼ�
	 * @param attrIndex ָ��������������
	 * @return ����������map
	 */
	public Map<String, Integer> valueCounts(ArrayList<ArrayList<String>> datas, int attrIndex){
		Map<String, Integer> valueCount = new HashMap<String, Integer>();
		String c = "";
		ArrayList<String> tuple = null;
		for (int i = 0; i < datas.size(); i++) {
			tuple = datas.get(i);
			c = tuple.get(attrIndex);
			if (valueCount.containsKey(c)) {
				valueCount.put(c, valueCount.get(c) + 1);
			} else {
				valueCount.put(c, 1);
			}
		}
		return valueCount;
	}
	
	/**
	 * ���datas��Ԫ����������������Ϣ����datas����
	 * @param datas ѵ��Ԫ��
	 * @return datas����ֵ
	 */
	public double infoD(ArrayList<ArrayList<String>> datas){
		double info = 0.000;
		int total = datas.size();
		Map<String, Integer> classes = valueCounts(datas, attrList.size());
		Iterator iter = classes.entrySet().iterator();
		Integer[] counts = new Integer[classes.size()];
		for(int i = 0; iter.hasNext(); i++)
		{
			Map.Entry entry = (Map.Entry) iter.next(); 
			Integer val = (Integer) entry.getValue(); 
			counts[i] = val;
		}
		for (int i = 0; i < counts.length; i++) {
			double base = DecimalCalculate.div(counts[i], total, 3);
			info += (-1) * base * Math.log(base);
		}
		return info;
	}
	/**
	 * ��ȡָ����������ָ��ֵ�������Ԫ��
	 * @param attrIndex ָ������������
	 * @param value ָ�������е�ֵ��
	 * @return ָ����������ָ��ֵ�������Ԫ��
	 */
	public ArrayList<ArrayList<String>> datasOfValue(int attrIndex, String value){
		ArrayList<ArrayList<String>> Di = new ArrayList<ArrayList<String>>();
		ArrayList<String> t = null;
		for (int i = 0; i < D.size(); i++) {
			t = D.get(i);
			if(t.get(attrIndex).equals(value)){
				Di.add(t);
			}
		}
		return Di;
	}
	
	/**
	 * ���ڰ�ָ�����Ի��ֶ�D��Ԫ���������Ҫ��������Ϣ
	 * @param attrIndex ָ�����Ե�����
	 * @return ��ָ�����Ի��ֵ�������Ϣֵ
	 */
	public double infoAttr(int attrIndex){
		double info = 0.000;
		ArrayList<String> values = getValues(D, attrIndex);
		for (int i = 0; i < values.size(); i++) {
			ArrayList<ArrayList<String>> dv = datasOfValue(attrIndex, values.get(i));
			info += DecimalCalculate.mul(DecimalCalculate.div(dv.size(), D.size(), 3), infoD(dv)); 
		}
		return info;
	}
	
	/**
	 * ��ȡ��ѷ������Ե�����
	 * @return ��ѷ������Ե�����
	 */
	public int bestGainAttrIndex(){
		int index = -1;
		double gain = 0.000;
		double tempGain = 0.000;
		for (int i = 0; i < attrList.size(); i++) {
			tempGain = infoD(D) - infoAttr(i);
			if (tempGain > gain) {
				gain = tempGain;
				index = i;
			}
		}
		return index;
	}
}
