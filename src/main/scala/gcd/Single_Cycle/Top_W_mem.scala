package Single_Cycle

import lab_4.ALU1
import chisel3 . _
import chisel3 . util . _

class Top_W_mem extends Module {
  val io = IO(new Bundle {
    val out = Output(UInt(32.W))
  })

  val datapath = Module(new DataPath)
  val insmem = Module(new InstMem("/home/abdulrehman/Desktop/single cycle/Scala-Chisel-Learning-Journey/src/main/scala/gcd/Single_Cycle/Imem.txt"))


//  val check = Reg(UInt(32.W))
//  check := datapath.io.Pcout
//  insmem.io.addr := check

  insmem.io.addr := datapath.io.Pcout
  datapath.io.insin := insmem.io.inst

  io.out := datapath.io.out



}