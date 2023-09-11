package Single_Cycle

import lab_4.ALU1
import chisel3 . _
import chisel3 . util . _

class DataPath extends Module {
  val io = IO(new Bundle {
    val out = Output(SInt(32.W))
  })
  io.out := 0.S

  val pc = Module(new PC)
  val cu = Module(new CU)
  val regfile = Module(new RegisterFile)
  val insmem = Module(new InstMem("C:/Users/Hamza's Son/Desktop/DSA SEM2 Java project/Scala-Chisel-Learning-Journey/src/main/scala/gcd/Single_Cycle/Imem.txt"))
  val datamem = Module(new Datamem)
  val alu = Module(new ALU1)


  insmem.io.addr := pc.io.nextaddr
  cu.io.ins := insmem.io.inst
  alu.io.alu_Op := Mux(cu.io.aluselect,0.U,cu.io.func)
  regfile.io.Wen := cu.io.RegWrite
  datamem.io.Wen := cu.io.MemWrite
  regfile.io.RD := cu.io.RD
  regfile.io.Rs1in := cu.io.Rs1
  regfile.io.Rs2in := cu.io.Rs2
  datamem.io.datain := regfile.io.Rs2out
  alu.io.in_A := regfile.io.Rs1out
  alu.io.in_B := Mux(!cu.io.Instype ,  cu.io.Imm, regfile.io.Rs2out)
  datamem.io.addr := alu.io.out.asUInt()
  datamem.io.datain := Mux(cu.io.wbselect, regfile.io.Rs2out, MuxLookup(cu.io.lengthselect, 0.S, Array(
    (0.U) -> regfile.io.Rs2out(8, 0).asSInt(),
    (1.U) -> regfile.io.Rs2out(15, 0).asSInt(),
    (2.U) -> regfile.io.Rs2out.asSInt())))


  regfile.io.datain := Mux(cu.io.wbselect, alu.io.out,MuxLookup(cu.io.lengthselect,0.S , Array(
    (0.U) -> datamem.io.dataout(8,0).asSInt(),
    (1.U) -> datamem.io.dataout(15,0).asSInt(),
    (2.U) -> datamem.io.dataout.asSInt())))
  io.out := alu.io.out


}