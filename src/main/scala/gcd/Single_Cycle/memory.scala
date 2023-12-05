package Single_Cycle

import chisel3 . _
import chisel3 . util . _
class memory extends Module {
  val io = IO(new Bundle {
    val Wen = Input(Bool())
    val addr = Input(UInt(32.W))
    val datain = Input(UInt(32.W))
    val dataout = Output(UInt(32.W))
    val fun3 = Input(UInt(3.W))
    val enable = Input(Bool())
    val instype = Input(UInt(2.W))

  })

  val datamem = Module(new Datamem)

  datamem.io.datain := io.datain
  datamem.io.Wen := io.datain
  datamem.io.addr := io.addr
  io.dataout := datamem.io.dataout
  datamem.io.fun3 := io.fun3
  datamem.io.enable := io.enable





}



