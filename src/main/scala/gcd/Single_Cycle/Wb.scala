package Single_Cycle
import chisel3 . _
import chisel3 . util . _


class Wb extends Module {
  val io = IO(new Bundle {

    val wbselect = Input(UInt(2.W))
    val datain = Input(UInt(32.W))
    val dataOut = Output(UInt(32.W))
    val pcin = Input(UInt(32.W))
    val memwrite = Input(Bool())


  })


  when(cu.io.wbselect === 1.U) {
    io.datain := alu.io.out
  }
    .elsewhen(cu.io.wbselect === 0.U) {
      regfile.io.datain := datamem.io.dataout
    }.elsewhen(cu.io.wbselect === 2.U) {
      regfile.io.datain := (pc + 4.U)
    }
}