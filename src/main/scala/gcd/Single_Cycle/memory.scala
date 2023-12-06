package Single_Cycle

import chisel3 . _
import chisel3 . util . _
class memory extends Module {
  val io = IO(new Bundle {
    val Wen = Input(Bool())
    val addr = Input(UInt(32.W))
    val datain = Input(UInt(32.W))
    val dataout = Output(UInt(32.W))
    val fun3 = Input(UInt(3.W)) //lengthselect
    val enable = Input(Bool())
    val instype = Input(UInt(2.W))
    val wbselectin = Input(UInt(2.W))
    val wbselectout = Input(UInt(2.W))
    val readmem = Input(Bool())
    val pcin = Input(UInt(32.W))
    val pcout = Output(UInt(32.W))


  })

  val datamem = Module(new Datamem)

  io.pcout := io.pcin

  datamem.io.datain := io.datain
  datamem.io.Wen := io.Wen
  datamem.io.addr := io.addr
  datamem.io.fun3 := io.fun3
  datamem.io.enable := io.enable

  io.dataout := Mux(io.readmem,datamem.io.dataout,io.datain)






}



