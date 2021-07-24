/**
 * @author 闫亮23
 * @version 1.0
 *   计算器 的 具体实现
 *   /** 逻辑判断
 *  *  在符号栈有符号情况下，如果当前的操作符的优先级小于或者等于栈中的操作符, 就需要从数栈中pop出两个数 运算,
 *  *  【特殊】这里需要判断是否此时的栈顶为左括号，如果是左括号不进入此循环
 *  *  设定的左括号是优先级大于加减乘除，所以当发现下一个进栈的符号的优先级比此时的栈顶的左括号优先级小的时候，
 *  *  应该让符号直接进栈，不进行弹出左符号的运算
 *  */

public class Calculator {
    String exp; // 计算表达式
    ArrayStack numberStack = new ArrayStack(50); //数字栈
    ArrayStack operateStack = new ArrayStack(50); // 字符栈

    int index =0;
    int num1 = 0;
    int num2 = 0;
    int oper = 0;
    int result = 0;
    char ch = ' '; //用于保存扫描 所得 字符
    String keepNum = ""; // 拼接 多位数；

    public Calculator(String exp) {
        this.exp = exp;
    }
    // 扫描 表达式 expression 方法 逻辑
    public void scanner(){
        while(true) {
            // 得到 每个 expression 的字符
            ch = exp.substring(index,index+1).charAt(0);//索引范围是从 0到length() - 1
            // 对 ch 做出相应处理
            if(operateStack.isOper(ch)){ // 是运算符
                if(!operateStack.isEmpty()){ // 栈 非空
                    if(operateStack.priority(ch) <= operateStack.priority(operateStack.peek()) & operateStack.peek()!=40){
                   num1 = numberStack.pop();
                   num2 = numberStack.pop();
                   oper = operateStack.pop();
                   result = numberStack.cal(num1,num2,oper);
                   // 新的计算结果 入栈
                   numberStack.push(result);
                   // 当前运算符入栈
                   operateStack.push(ch);
               }else if(ch==41){ // 这里进行 有括号 的判断，遇到 又括号 需要先 进行 括号内运算
                   operateStack.push(ch); // 右括号 进栈；
                   if(ch==41){
                       int operR = operateStack.pop(); //operR 表示 右括号
                       while(true){
                           // 进行运算
                          num1 = numberStack.pop();
                          num2 = numberStack.pop();
                          oper = operateStack.pop();
                          result = numberStack.cal(num1,num2,oper);

                           // 新的计算结果 入栈
                           numberStack.push(result);
                           // 判断当前 符号是否为 '(' ， 是 则 弹出，并结束循环
                           if(operateStack.peek()==40){
                              int operL = operateStack.pop();
                               break;
                           }
                       }
                   }
               }else{ // 若 优先级 大于 栈内符号 ，直接入栈
                   operateStack.push(ch);
               }

             } else{ // 符号栈为 空，直接入栈
                    operateStack.push(ch);
                }
          }  else{ // 数字的话 入 数栈
                //numberStack.push(ch);
                // 使用 索引 index，+1 来判断 下一位是不是 数，
                // 是数就进行扫描并拼接

                keepNum += ch;

                // 已扫描到 最后一位 的话 直接入栈
                if(index == exp.length()-1){
                    numberStack.push(Integer.parseInt(keepNum));
                }else { // 判断下一位 是数字还是 符号, 是 符号 就将 keepNum 入栈
                    if(operateStack.isOper(exp.substring(index+1,index+2).charAt(0))){
                        numberStack.push(Integer.parseInt(keepNum));
                        // 要将 keepNum 清空 ，下次进行拼接，否则出错
                        keepNum = "";
                    }
                }
            }
            index++;
              if(index >= exp.length()){
                  break;
              }
        }

        // 都扫瞄完毕后  顺序开始计算
        // 若 符号栈 空了，说明 计算结束， 数栈中 最后一个数 就是结果
        while(true){
            if(operateStack.isEmpty()){
                break;
            }
            num1 = numberStack.pop();
            num2 = numberStack.pop();
            oper = operateStack.pop();
            result = numberStack.cal(num1,num2,oper);
            numberStack.push(result);
        }
         int finalResult = numberStack.pop();
        System.out.printf("表达式 %s = %d", exp,finalResult);
    }
}
