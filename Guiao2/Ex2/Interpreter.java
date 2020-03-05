import java.util.Stack;

public class Interpreter extends SuffixCalculatorBaseVisitor<String> {

    private static char sig = 'a';
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
      if(sig != 'a' && stk.size()>=2){
        int result = 0;
          switch(sig){
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
          sig = 'a';
      }
      return visitChildren(ctx);
   }

   @Override public String visitExprSuffix(SuffixCalculatorParser.ExprSuffixContext ctx) {
      System.out.println(ctx.getText());
      sig = ctx.getText().charAt(ctx.getText().length()-1);

      return visitChildren(ctx);
   }
}
