package Single_Cycle
import chisel3 . _
import chisel3 . util . _
class RegisterFile  extends Module {
  val io = IO(new Bundle {
    val Wen = Input(Bool())
    val RD = Input(UInt(5.W))
    val Rs1in = Input(UInt(5.W))
    val Rs2in = Input(UInt(5.W))
    val Rs1out = Output(SInt(32.W))
    val Rs2out = Output(SInt(32.W))
    val datain = Input(SInt(32.W))
  })

  val regFile = Mem (32 , SInt(32.W ) )
  io.Rs1out := 0.S
  io.Rs2out := 0.S


  when(io.Wen && (io.RD =/= 0.U)){
    regFile.write(io.RD,io.datain)
    io.Rs1out := regFile.read(io.Rs1in)
    io.Rs2out := regFile.read(io.Rs2in)
  }
    .otherwise{
      io.Rs1out := regFile.read(io.Rs1in)
      io.Rs2out := regFile.read(io.Rs2in)

    }

}