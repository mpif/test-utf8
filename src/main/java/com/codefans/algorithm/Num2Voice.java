package com.codefans.algorithm;

/**
 * Created by caishengzhi on 2017/2/17 16:36.
 */
public class Num2Voice {

    public static void main(String[] args){
        int maxInt = Integer.MAX_VALUE;
        System.out.println(maxInt);

        Num2Voice num2Voice = new Num2Voice();
        System.out.println("voiceString:" + num2Voice.num2voice(760032103));
    }

    public String num2voice(int num) {
        StringBuilder sb = new StringBuilder();
        num2voice(num, sb);
        return sb.toString();
    }

    public void num2voice(int num, StringBuilder sb) {
        if(num / 1000000000 > 0) {
            sb.append(smallInt2BigIntStr(num/1000000000) + "十");
            if(num%1000000000 >= 0 && num%1000000000 < 100000000) {
                sb.append("零");
            }
            num2voice(num%1000000000, sb);
        } else
        if(num / 100000000 > 0) {
            sb.append(smallInt2BigIntStr(num/100000000) + "亿");
            if(num%100000000 >= 0 && num%100000000 < 10000000) {
                sb.append("零");
            }
            num2voice(num%100000000, sb);
        } else
        if(num / 10000000 > 0) {
            sb.append(smallInt2BigIntStr(num/10000000) + "千");
            if(num%10000000 >= 0 && num%10000000 < 1000000) {
                sb.append("零");
            }
            num2voice(num%10000000, sb);
        } else
        if(num / 1000000 > 0) {
            sb.append(smallInt2BigIntStr(num/1000000) + "百");
            if(num%1000000 >= 0 && num%1000000 < 100000) {
                sb.append("零");
            }
            num2voice(num%1000000, sb);
        } else
        if(num / 100000 > 0) {
            sb.append(smallInt2BigIntStr(num/100000) + "十");
            if(num%100000 >= 0 && num%100000 < 10000) {
                sb.append("零");
            }
            num2voice(num%100000, sb);
        } else
        if(num / 10000 > 0) {
            sb.append(smallInt2BigIntStr(num/10000) + "万");
            if(num%10000 >= 0 && num%10000 < 1000) {
                sb.append("零");
            }
            num2voice(num%10000, sb);
        } else if(num / 1000 > 0) {
            sb.append(smallInt2BigIntStr(num/1000) + "千");
            if(num%1000 >= 0 && num%1000 < 100) {
                sb.append("零");
            }
            num2voice(num%1000, sb);
        } else if(num / 100 > 0) {
            sb.append(smallInt2BigIntStr(num/100) + "百");
            if(num%100 >= 0 && num%100 < 10) {
                sb.append("零");
            }
            num2voice(num%100, sb);
        }  else if(num / 10 > 0) {
            sb.append(smallInt2BigIntStr(num/10) + "十");
            if(num%10 >= 0 && num%10 < 1) {
                sb.append("零");
            }
            num2voice(num%10, sb);
        } else {
            if(num == 0) {
                sb.append("");
            } else {
                sb.append(smallInt2BigIntStr(num));
            }

            return;
        }
    }

    public String smallInt2BigIntStr(int num) {
        String result = "";
        if(num == 0) {
            result = "零";
        } else if (num == 1) {
            result = "一";
        } else if (num == 2) {
            result = "二";
        } else if (num == 3) {
            result = "三";
        } else if (num == 4) {
            result = "四";
        } else if (num == 5) {
            result = "五";
        } else if (num == 6) {
            result = "六";
        } else if (num == 7) {
            result = "七";
        } else if (num == 8) {
            result = "八";
        } else if (num == 9) {
            result = "九";
        }
        return result;
    }
}
