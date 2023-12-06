package Single_Cycle

import lab_4.ALU1
import chisel3 . _
import chisel3 . util . _

class TopCore_5stage extends Module {
  val io = IO(new Bundle {
    val out = Output(UInt(32.W))
  })
  val Fetch = Module(new Fetch)
  val Decode = Module(new Decode)
  val Execute = Module(new Execute)
  val Memory = Module(new memory)
  val Wb = Module(new Wb)


  Fetch.io.pcout






}