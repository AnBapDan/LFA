public class Interpreter extends CalculatorBaseVisitor<String> {

   @Override public String visitProgram(CalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStat(CalculatorParser.StatContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitExprParent(CalculatorParser.ExprParentContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      return visitChildren(ctx);
   }
}
