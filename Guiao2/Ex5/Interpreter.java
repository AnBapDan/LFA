import java.util.HashMap;


public class Interpreter extends CalculatorBaseVisitor<Integer> {
   HashMap<String, Integer> vars = new HashMap<>();
   @Override public Integer visitProgram(CalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Integer visitExpression(CalculatorParser.ExpressionContext ctx) {
     if(ctx.expr()!=null){
       int x = visit(ctx.expr());
       System.out.println("Resultado: "+x);
     }
      return 0;
   }

   @Override public Integer visitVariable(CalculatorParser.VariableContext ctx) {
     if(ctx.assignment()==null){
       return -1;
     }
     visit(ctx.assignment());
     System.out.println("Var added");
     return 0;
   }

   @Override public Integer visitAssignment(CalculatorParser.AssignmentContext ctx) {
     if(ctx.expr()==null){
       return -1;
     }
     int x = visit(ctx.expr());
     vars.put(ctx.ID().getText(), x);

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

   @Override public Integer visitExprId(CalculatorParser.ExprIdContext ctx) {
      return vars.get(ctx.ID().getText());
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
