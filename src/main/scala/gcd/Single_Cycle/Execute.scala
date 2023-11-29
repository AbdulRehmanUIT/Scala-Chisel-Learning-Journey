package Single_Cycle
import chisel3 . _
import chisel3 . util . _
import lab_4.ALU1



trait Config{
  val WLEN = 32
  val ALUOP_SIG_LEN = 4
}



class Execute extends Module with Config {
  val io = IO(new Bundle {
    val in_A = Input(UInt(WLEN.W))
    val in_B = Input(UInt(WLEN.W))
    val alu_Op = Input(UInt(ALUOP_SIG_LEN.W))
    val out = Output(UInt(WLEN.W))
//    val in_A = Input(UInt(32.W))
//    val in_B = Input(UInt(32.W))
    val fun3 = Input(UInt(4.W))
    val doBranch = Output(Bool())
    val isBtype = Input(Bool())
    //val sum = Output(UInt(WLEN.W))

  })

  val Alu = Module(new ALU1)
  val BALU = Module(new BranchALU)

  Alu.io.in_A := io.in_A
  Alu.io.in_B := io.in_B
  BALU.io.in_A := io.in_A
  BALU.io.in_B := io.in_B

  Alu.io.alu_Op := io.alu_Op
  BALU.io.fun3 := io.fun3
  io.out := Alu.io.out
  io.doBranch := BALU.io.doBranch
  BALU.io.isBtype := io.isBtype


}
