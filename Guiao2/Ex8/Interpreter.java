import java.util.HashMap;

public class Interpreter extends CalculatorBaseVisitor<Fraction> {
   HashMap<String, Fraction> vars = new HashMap<>();

   @Override public Fraction visitProgram(CalculatorParser.ProgramContext ctx) {
      visitChildren(ctx);
      return null;
   }

   @Override public Fraction visitExpression(CalculatorParser.ExpressionContext ctx) {
     if(ctx.expr()!=null){
       Fraction x = visit(ctx.expr());
     }
      return null;
   }

   @Override public Fraction visitPrintln(CalculatorParser.PrintlnContext ctx) {
     if(ctx.print()==null){
       return null;
     }
     visit(ctx.print());
     return null;
   }

   @Override public Fraction visitVariable(CalculatorParser.VariableContext ctx) {
     if(ctx.assignment()==null){
       return null;
     }
     visit(ctx.assignment());
     return null;
   }

   @Override public Fraction visitPrint(CalculatorParser.PrintContext ctx) {
     Fraction a = visit(ctx.expr());
     System.out.println("\""+a.getN()+"/"+a.getD() +"\"");
     return null;
   }

   @Override public Fraction visitAssignment(CalculatorParser.AssignmentContext ctx) {
     if(ctx.expr()==null){
       return null;
     }
     Fraction x = visit(ctx.expr());
     vars.put(ctx.ID().getText(), x);
     return null;
   }

   @Override public Fraction visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
     Fraction v1 = visit(ctx.expr(0));
     Fraction v2 =  visit(ctx.expr(1));
     Fraction result = new Fraction(1,null);
     switch(ctx.op.getText()){
       case "+":
          Integer n = v1.getN()*v2.getD()+v2.getN()*v1.getD();
          Integer d = v1.getD()*v2.getD();
          result = new Fraction(n,d);
          break;
       case "-":
          n = v1.getN()*v2.getD()-v2.getN()*v1.getD();
          d = v1.getD()*v2.getD();
          result = new Fraction(n,d);
          break;
     }
     return result;
   }

   @Override public Fraction visitExprFracc(CalculatorParser.ExprFraccContext ctx) {
      return new Fraction(Integer.parseInt(ctx.n.getText()),Integer.parseInt(ctx.d.getText()));
   }

   @Override public Fraction visitExprIntegerSignal(CalculatorParser.ExprIntegerSignalContext ctx) {
     Fraction f = visit(ctx.expr());
     switch(ctx.op.getText()){
       case "+": return f;
       case "-":
          f.setN(-1*f.getN());
          return f;
     }
     return null;
   }

   @Override public Fraction visitExprParent(CalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Fraction visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return new Fraction(Integer.parseInt(ctx.getText()),null);
   }

   @Override public Fraction visitExprId(CalculatorParser.ExprIdContext ctx) {
      return vars.get(ctx.ID().getText());
   }

   @Override public Fraction visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      Fraction v1 = visit(ctx.expr(0));
      Fraction v2 = visit(ctx.expr(1));
      Fraction result = new Fraction(1,null);;
      switch(ctx.op.getText()){
        case "*":
          Integer n = v1.getN()*v2.getN();
          Integer d = v1.getD()*v2.getD();
          result = new Fraction(n,d);
          break;
        case ":":
          n = Math.round(v1.getN()/v2.getN());
          d = Math.round(v1.getD()/v2.getD());
          result = new Fraction(n,d);
          break;
      }
       System.out.print(ctx.op.getText());
      return result;
   }
}
