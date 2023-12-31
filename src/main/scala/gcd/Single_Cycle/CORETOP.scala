package Single_Cycle

import lab_4.ALU1
import chisel3 . _
import chisel3 . util . _

class CORETOP extends Module {
  val io = IO(new Bundle {
    val out = Output(UInt(32.W))
  })

  val Memory = Module(new InstMem("C:\\Users\\Hamza's Son\\Desktop\\5 stage\\RV32I--5-Stage---Pipeline-\\src\\main\\scala\\gcd\\Single_Cycle\\Imem.txt"))
  val Core = Module(new TopCore_5stage)

  Memory.io.addr := Core.io.pcout
  Core.io.insin := Memory.io.inst
  io.out := Core.io.wbout
}