public class Fraction{

  private Integer n,d;

  Fraction(Integer n, Integer d ){
    this.n=n;
    if(d == null){
      this.d = 1;
    }
    else if(d == 0){
      System.err.println("Cannot divide by 0!");
    }
    else{
      this.d = d;
    }
    simplifySignal();
  }

  public Integer getN(){
    return n;
  }

  public void setN(int n){
    this.n = n;
    simplifySignal();
  }

  public Integer getD(){
    return d;
  }

  private void simplifySignal(){
    if(getN()> 0 && getD()> 0 || getN()<0 && getD()<0){
      this.n = Math.abs(getN());
      this.d = Math.abs(getD());
    }
    else{
      this.d = Math.abs(getD());
      this.n = -Math.abs(getN());

    }
  }

}
