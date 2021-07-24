/**
 * @author 闫亮23
 * @version 1.0
 *
 *   定义一个 栈 供 存放 字符，数
 *   1.空栈的top为什么=-1而不是=0?
 *    因为  0 表示 第一个元素，并不为空。
 *
 */
public class ArrayStack {

    private int maxSize;
    private int[] stack;
    private int top = -1;

    // 构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize]; // 大小为 maxSize 的 int数组
    }

    /**  栈 具体功能实现
     *  1.判断栈满
     *  2.判断栈空
     *  3.pop出栈
     *  4.push进栈
     *  5.返回栈顶元素
     *  6.遍历栈
     *  7.定义优先级
     *  8.计算逻辑
     *  9.判断是否是 运算符
     **/

    // 返回当前 栈顶值的 方法
    int peek(){
        return stack[top];  // 这是 top 不是 -1 ；
    }

    // 判断栈是否满
     boolean isFull(){
       return top == maxSize-1; //如果 满足条件 返回 true
//        if(top == maxSize-1) {         // 意思一样
//            return true;
//        }
//        else { return false;}
    }

    // 判断栈是否空
    boolean isEmpty() {
        return top == -1;
    }

    // 入栈操作 push
    void push(int val){

        if(isFull()){
            System.out.println("栈已满~~");
            return; // 直接返回
        }
        top++; // 这是栈未满的加入逻辑
        stack[top] = val;
    }

    // 出栈 pop , 返回栈顶数据
//    int pop(){
//        if(isEmpty()){
//            throw new RuntimeException("栈空无元素");
//        }
//        int val = stack[top];
//        top--;
//        return val;
//    }
    int pop() {
        //先判断栈是否空
        if(isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");

        }
        int value = stack[top];
        top--;
        return value;
    }

    // 遍历栈 来显示 栈的情况
    private void list(){
        if(isEmpty()){
            System.out.println("栈空无元素");
            return;
        }
        // 从栈顶 开始 显示数据
        for (int i = top; i >= 0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    // 自定义来返回优先级，当前 运算符 小于等于 栈中的，就要跳出 数先运算
    int priority(int oper){
        if(oper == '(' || oper == ')'){
            return 2;  // 括号 优先级最大，
        } else if(oper == '*' || oper == '/'){
            return 1;
        } else if(oper == '+' || oper == '-'){
            return 0;
        } else {
            return -1; // 其他不符合条件
        }
    }

    // 判断是否为 运算符
    boolean isOper(char val){
        return val =='+' ||  val =='-' ||val =='*' || val =='/' || val =='(' || val ==')';
    }

    // 计算的方法
    int cal(int number1, int number2, int oper){
        int result = 0; // 结果
        switch (oper){
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number2 - number1; // 这里 害死我了， 查了 五遍代码 ，顺序出错
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                result = number2 / number1;
                break;
            default:
                break;
        }
        return result;
    }

}
