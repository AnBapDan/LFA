import java.util.Stack;

public class Interpreter extends SuffixCalculatorBaseVisitor<String> {

    private static Stack<Integer> stk = new Stack<>();

   @Override public String visitProgram(SuffixCalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStat(SuffixCalculatorParser.StatContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitExprNumber(SuffixCalculatorParser.ExprNumberContext ctx) {
      stk.push(Integer.parseInt(ctx.Number().getText()));
      System.out.println("Adicionou");
      return visitChildren(ctx);
   }

   @Override public String visitExprSuffix(SuffixCalculatorParser.ExprSuffixContext ctx) {
      System.out.println(ctx.getText());
      int result = 0;
      if(!stk.isEmpty()){
        switch(ctx.getText().charAt(2)){
          case '+': result = stk.pop() + stk.pop();
                    break;
          case '-': result = stk.pop() - stk.pop();
                    break;
          case '*': result = stk.pop() * stk.pop();
                    break;
          case '/': result = Math.round(stk.pop()/stk.pop());
                    break;

        }
        stk.push(result);
        System.out.println(result);
      }
      return visitChildren(ctx);
   }
}
