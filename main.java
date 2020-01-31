import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class main {



    public static void main(String args[]) throws IOException {
        System.out.println("This file reads ./input.txt writes ./output.hex");
        File output = new File("./output.hex");
        File file = null;
        FileWriter writer = new FileWriter((output),false);
        writer.write("v2.0 raw\n");
        Scanner sc=null;
         file = new File("input.txt");
      sc = new Scanner(file);


        while(sc.hasNext()){
            String line = sc.nextLine();
            String[] split =line.split("[ \\,]");
            String instruction="";

            if(split[0].equals("ADD") ||split[0].equals("AND")
        ||split[0].equals("OR")||split[0].equals("XOR")){
String operation="";
                switch(split[0]){
                    case "ADD":
operation="1000";
break;
                    case "AND":
                        operation="1001";
                        break;
                    case "OR":
                        operation="1010";
                        break;

                        case "XOR":
                        operation="1011";
                        break;
                }

 instruction+=operation;
String dest=Integer.toBinaryString( Integer.parseInt(split[1].substring(1)));
while(dest.length()!=4){
    dest="0"+dest;
}
  String  firstSource = Integer.toBinaryString(Integer.parseInt(split[2].substring(1)));
    while(firstSource.length()!=4){
        firstSource="0"+firstSource;
    }
    String  secondSource = Integer.toBinaryString(Integer.parseInt(split[3].substring(1)));
    while(secondSource.length()!=6){
        secondSource="0"+secondSource;
    }
    instruction+=dest+firstSource+secondSource;

}

else if(split[0].equals("ADDI") ||split[0].equals("ANDI")
                    ||split[0].equals("ORI")||split[0].equals("XORI")){
    String operation="";
    switch(split[0]){
        case "ADDI":
            operation="1000";
            break;
        case "ANDI":
            operation="1001";
            break;
        case "ORI":
            operation="1010";
            break;

        case "XORI":
            operation="1011";
            break;
    }


    instruction+=operation;
    String dest=Integer.toBinaryString( Integer.parseInt(split[1].substring(1)));
    while(dest.length()!=4){
        dest="0"+dest;
    }
    String  firstSource = Integer.toBinaryString(Integer.parseInt(split[2].substring(1)));
    while(firstSource.length()!=4){
        firstSource="0"+firstSource;
    }
    String  imm = Integer.toBinaryString(Integer.parseInt(split[3]));

    while(imm.length()!=5){
        if(imm.length()>5){
            imm=imm.substring(27);

            break;}
        imm="0"+imm;
    }
    imm="1"+imm;


                instruction+=dest+firstSource+imm;


            }
      else      if(split[0].equals("JUMP")){
String operation ="1100";
                instruction+=operation;
                String addr=Integer.toBinaryString( Integer.parseInt(split[1]));
                while(addr.length()!=14){
                    if(addr.length()>14){
                        addr=addr.substring(18);

                    break;}
                    addr="0"+addr;
                }
                instruction+=addr;
            }
      else{
            String dest=Integer.toBinaryString( Integer.parseInt(split[1].substring(1)));
            while(dest.length()!=4){
                dest="0"+dest;
            }
            String source;
            String ADDR;
            String op1;
            String op2;
            String operation="default";
          switch (split[0]){

              case "LD":
                  operation="1110";
                  source=Integer.toBinaryString( Integer.parseInt(split[1].substring(1)));
                  while(source.length()<4)
                      source="0"+source;
                  ADDR = Integer.toBinaryString( Integer.parseInt(split[2]));;
                  while(ADDR.length()!=10){
                      if(ADDR.length()>10){
                          ADDR=ADDR.substring(22);

                          break;}
                      ADDR="0"+ADDR;
                  }
                  instruction=operation+source+ADDR;

                  break;
              case "ST":
                  operation ="1101";
              source=Integer.toBinaryString( Integer.parseInt(split[1].substring(1)));
              while(source.length()<4)
                  source="0"+source;
                   ADDR = Integer.toBinaryString( Integer.parseInt(split[2]));;
                  while(ADDR.length()!=10){
                      if(ADDR.length()>10){
                          ADDR=ADDR.substring(22);

                          break;}
                      ADDR="0"+ADDR;
                  }
                  instruction=operation+source+ADDR;

                  break;
              case "BEQ":
                  operation="0010";

              case "BLT":
                  if(operation.equals("default"))
                      operation="0011";
              case "BGT":
                  if(operation.equals("default"))
                      operation="0100";
              case "BLE":
                  if(operation.equals("default"))
                      operation="0101";
              case "BGE":
                  if(operation.equals("default"))
                      operation="0110";
                  op1=Integer.toBinaryString( Integer.parseInt(split[1].substring(1)));
                  while(op1.length()<4){
                      op1="0"+op1;
                  }
                  op2=Integer.toBinaryString( Integer.parseInt(split[2].substring(1)));
                  while(op2.length()<4){
                      op2="0"+op2;
                  }
                  ADDR=Integer.toBinaryString( Integer.parseInt(split[3]));

                  while(ADDR.length()!=6){
                      if(ADDR.length()>6){
                          ADDR=ADDR.substring(26);

                          break;}
                      ADDR="0"+ADDR;
                  }
                  instruction=operation+op1+op2+ADDR;

                  break;
          }

      }

            String hexString = new BigInteger(instruction.substring(0,2), 2).toString(16) +""
                    +new BigInteger(instruction.substring(2,6), 2).toString(16) +""+
                    new BigInteger(instruction.substring(6,10), 2).toString(16
                    )+"" +new BigInteger(instruction.substring(10,14), 2).toString(16)+""
                    +new BigInteger(instruction.substring(14), 2).toString(16);
            writer.write(hexString+"\n");
        }

writer.close();
    }

}
