package com.atxin.glmall.tre;
import java.math.BigDecimal;   

public class DecimalCalculate {   
/**  
* ����Java�ļ����Ͳ��ܹ���ȷ�ĶԸ������������㣬����������ṩ��  
* ȷ�ĸ��������㣬�����Ӽ��˳����������롣  
*/   
//Ĭ�ϳ������㾫��   
private static final int DEF_DIV_SCALE = 10;   
      
	//����಻��ʵ����   
	private DecimalCalculate(){   
	}   
	    /**  
	     * �ṩ��ȷ�ļӷ����㡣  
	     * @param v1 ������  
	     * @param v2 ����  
	     * @return ���������ĺ�  
	     */   
	    public static double add(double v1,double v2){   
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));   
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));   
	        return b1.add(b2).doubleValue();   
	    }   
	    /**  
	     * �ṩ��ȷ�ļ������㡣  
	     * @param v1 ������  
	     * @param v2 ����  
	     * @return ���������Ĳ�  
	     */   
	    public static double sub(double v1,double v2){   
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));   
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));   
	        return b1.subtract(b2).doubleValue();   
	    }   
	    /**  
	     * �ṩ��ȷ�ĳ˷����㡣  
	     * @param v1 ������  
	     * @param v2 ����  
	     * @return ���������Ļ�  
	     */   
	    public static double mul(double v1,double v2){   
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));   
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));   
	        return b1.multiply(b2).doubleValue();   
	    }   
	    /**  
	     * �ṩ����ԣ���ȷ�ĳ������㣬�����������������ʱ����ȷ��  
	     * С�����Ժ�10λ���Ժ�������������롣  
	     * @param v1 ������  
	     * @param v2 ����  
	     * @return ������������  
	     */   
	    public static double div(double v1,double v2){   
	        return div(v1,v2,DEF_DIV_SCALE);   
	    }   
	    /**  
	     * �ṩ����ԣ���ȷ�ĳ������㡣�����������������ʱ����scale����ָ  
	     * �����ȣ��Ժ�������������롣  
	     * @param v1 ������  
	     * @param v2 ����  
	     * @param scale ��ʾ��ʾ��Ҫ��ȷ��С�����Ժ�λ��  
	     * @return ������������  
	     */   
	    public static double div(double v1,double v2,int scale){   
	        if(scale<0){   
	            throw new IllegalArgumentException(   
	                "The scale must be a positive integer or zero");   
	        }   
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));   
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));   
	        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();   
	    }   
	    /**  
	     * �ṩ��ȷ��С��λ�������봦��  
	     * @param v ��Ҫ�������������  
	     * @param scale С���������λ  
	     * @return ���������Ľ��  
	     */   
	    public static double round(double v,int scale){   
	        if(scale<0){   
	            throw new IllegalArgumentException(   
	                "The scale must be a positive integer or zero");   
	        }   
	        BigDecimal b = new BigDecimal(Double.toString(v));   
	        BigDecimal one = new BigDecimal("1");   
	        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();   
	    }   
	      
	   /**  
	    * �ṩ��ȷ������ת��(Float)  
	    * @param v ��Ҫ��ת��������  
	    * @return ����ת�����  
	    */   
	    public static float convertsToFloat(double v){   
	    BigDecimal b = new BigDecimal(v);   
	    return b.floatValue();   
	    }   
	    /**  
	* �ṩ��ȷ������ת��(Int)��������������  
	* @param v ��Ҫ��ת��������  
	* @return ����ת�����  */   
	public static int convertsToInt(double v){   
	BigDecimal b = new BigDecimal(v);   
	    return b.intValue();   
	}   
	/**  
	* �ṩ��ȷ������ת��(Long)  
	* @param v ��Ҫ��ת��������  
	* @return ����ת�����  
	*/   
	public static long convertsToLong(double v){   
	BigDecimal b = new BigDecimal(v);   
	    return b.longValue();   
	}   
	/**  
	* �����������д��һ��ֵ  
	* @param v1 ��Ҫ���Աȵĵ�һ����  
	* @param v2 ��Ҫ���Աȵĵڶ�����  
	* @return �����������д��һ��ֵ  
	*/   
	public static double returnMax(double v1,double v2){   
	BigDecimal b1 = new BigDecimal(v1);   
	BigDecimal b2 = new BigDecimal(v2);   
	    return b1.max(b2).doubleValue();   
	}   
	/**  
	* ������������С��һ��ֵ  
	* @param v1 ��Ҫ���Աȵĵ�һ����  
	* @param v2 ��Ҫ���Աȵĵڶ�����  
	* @return ������������С��һ��ֵ  
	*/   
	public static double returnMin(double v1,double v2){   
	BigDecimal b1 = new BigDecimal(v1);   
	BigDecimal b2 = new BigDecimal(v2);   
	    return b1.min(b2).doubleValue();   
	}  
 /**  
	* ��ȷ�Ա���������  
	* @param v1 ��Ҫ���Աȵĵ�һ����  
	* @param v2 ��Ҫ���Աȵĵڶ�����  
	* @return ���������һ���򷵻�0�������һ�����ȵڶ��������򷵻�1����֮����-1  */   
	public static int compareTo(double v1,double v2){   
	BigDecimal b1 = new BigDecimal(v1);   
	BigDecimal b2 = new BigDecimal(v2);   
	    return b1.compareTo(b2);   
	}   
}
