public class InterpreterF extends CalculatorBaseVisitor<Integer> {

   @Override public Integer visitProgram(CalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Integer visitStat(CalculatorParser.StatContext ctx) {
     if(ctx.expr()!=null){
       int x =visit(ctx.expr());
       System.out.println("Resultado: "+x);
     }
      return 0;
   }

   @Override public Integer visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
     int v1 = visit(ctx.expr(0));
     int v2 = visit(ctx.expr(1));
     int result =0;
     switch(ctx.op.getText()){
       case "+": result = v1 + v2; break;
       case "-": result = v1 - v2; break;
     }
     return result;
   }

   @Override public Integer visitExprIntegerSignal(CalculatorParser.ExprIntegerSignalContext ctx) {
     int value = visit(ctx.expr());
     switch(ctx.op.getText()){
       case "+": return value;
       case "-": return -value;
     }

      return -1;
   }

   @Override public Integer visitExprParent(CalculatorParser.ExprParentContext ctx) {

      return visit(ctx.expr());
   }

   @Override public Integer visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return Integer.parseInt(ctx.Integer().getText());
   }

   @Override public Integer visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      int v1 = visit(ctx.expr(0));
      int v2 = visit(ctx.expr(1));
      int result =0;
      switch(ctx.op.getText()){
        case "*": result = v1 * v2; break;
        case "/": result = Math.round(v1/v2); break;
        case "%": result = Math.round(v1%v2); break;
      }
      return result;
   }
}
