package Single_Cycle
import chisel3 . _
import chisel3 . util . _


class Wb extends Module {
  val io = IO(new Bundle {

    val wbselect = Input(UInt(2.W))
    val Aluin = Input(UInt(32.W))

  })
}