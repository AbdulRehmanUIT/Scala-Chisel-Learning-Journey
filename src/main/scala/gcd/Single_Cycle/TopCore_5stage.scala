package Single_Cycle

import chisel3._

class TopCore_5stage extends Module {
  val io = IO(new Bundle {
    //val executeout = Output(UInt(32.W))
    //val fetchout = Output(UInt(32.W))
    //val memout = Output(UInt(32.W))
    val wbout = Output(UInt(32.W))
    val pcout = Output(UInt(32.W))
    val insin = Input(UInt(32.W))

  })
  //val ForwardUnit = Module(new Forward)
  val Fetch = Module(new Fetch)
  val Decode = Module(new Decode)
  val Execute = Module(new Execute)
  val Memory = Module(new memory)
  dontTouch(Memory.io)
  dontTouch(Fetch.io)
  dontTouch(Decode.io)
  val Wb = Module(new Wb)

  io.pcout := Fetch.io.pcout
  Fetch.io.insin := io.insin





  //IF_ID pipeline
  val ins = Reg(UInt(32.W))
  ins:=Fetch.io.ins
  Decode.io.ins:=ins
  val pc = Reg(UInt(32.W))
  pc:= Fetch.io.pcout
  Decode.io.pcin:=pc
  Fetch.io.aluout := 0.U
  Fetch.io.pcselect:=0.U
  Decode.io.RDin := 0.U
  Decode.io.datain:= 0.U
  Decode.io.RegWritein := 0.U
  Decode.io.dobranch:=Execute.io.doBranch

  //io.fetchout:= ins

// Forward module
//  val rs1sel = Reg(UInt(5.W))
//  rs1sel := Decode.io.Rs1sel
//  ForwardUnit.io.ID_EX_REGRS1 := rs1sel
//
//  val rs2sel = Reg(UInt(5.W))
//  rs2sel := Decode.io.Rs2sel
//  ForwardUnit.io.ID_EX_REGRS2 := rs2sel
//  ForwardUnit.io.MEM_WB_REGRD := Rd4
//  ForwardUnit.io.MEM_WB_REGWR := Regwrite3
//  ForwardUnit.io.EX_MEM_REGRD := RDin
//  ForwardUnit.io.EX_MEM_REGWR := Regwrite2


//  //ID_IE pipeline
//  Decode.io <> Execute.io

//  val pc2 = Reg(UInt(32.W))
//  pc2:= Decode.io.pcout
  Execute.io.pcin:=Decode.io.pcout
//  val RD = Reg(UInt(5.W))
//  RD := Decode.io.RD
  Execute.io.RDin:= Decode.io.RD
//  val imm = Reg(UInt(32.W))
//  imm := Decode.io.Imm
  Execute.io.Imm := Decode.io.Imm
//  val Instype = Reg(Bool())
//  Instype:= Decode.io.Instype
  Execute.io.Instype := Decode.io.Instype
//  val RegWriteout = Reg(Bool())
//  RegWriteout:=Decode.io.RegWriteout
  Execute.io.RegWritein:=Decode.io.RegWriteout
//  val MemWrite = Reg(Bool())
//  MemWrite := Decode.io.MemWrite
  Execute.io.MemWritein := Decode.io.MemWrite
//  val func = Reg(UInt(5.W))
//  func := Decode.io.func
  Execute.io.funcin:= Decode.io.func
//  val wbselect = Reg(UInt(2.W))
//  wbselect:= Decode.io.wbselect
  Execute.io.wbselectin:=Decode.io.wbselect
//  val aluselect = Reg(Bool())
//  aluselect:= Decode.io.aluselect
  Execute.io.aluselect:= Decode.io.aluselect
//  val lengthselect = Reg(UInt(2.W))
//  lengthselect:= Decode.io.lengthselect
  Execute.io.lengthselectin:= Decode.io.lengthselect
//  val btypefun = Reg(UInt(4.W))
//  btypefun:= Decode.io.btypefun
  Execute.io.fun3 := Decode.io.btypefun
//  val pcselec = Reg(Bool())
//  pcselec:= Decode.io.pcselec
  Execute.io.pcselec:=Decode.io.pcselec
//  val btype = Reg(Bool())
//  btype := Decode.io.btype
  Execute.io.isBtype:=Decode.io.btype
//  val jump = Reg(Bool())
//  jump := Decode.io.jump
  Execute.io.jump := Decode.io.jump
//  val readmem = Reg(Bool())
//  readmem:=Decode.io.readmem
  Execute.io.readmemin:=Decode.io.readmem
//  val in_A = Reg(UInt(32.W))
//  val in_B = Reg (UInt(32.W))
//  in_A := Decode.io.Rs1out
//  in_B := Decode.io.Rs2out
  Execute.io.in_A := Decode.io.Rs1out
  Execute.io.in_B := Decode.io.Rs2out
//
//  io.executeout := Execute.io.out


  //IE_MEM
  //Execute.io <> Memory.io
//  val pcselc3 = Reg(Bool())
//  pcselc3:= Execute.io.pcselecout
  Memory.io.pcselecin :=  Execute.io.pcselecout
//  val addr = Reg(UInt(32.W)) //main output of alu
//  addr := Execute.io.out
  Memory.io.addr:=Execute.io.out
//  val pc3= Reg(UInt(32.W))
//  pc3:= Execute.io.pcout
  Memory.io.pcin:=Execute.io.pcout
//  val Regwrite2 = Reg(Bool())
//  Regwrite2:= Execute.io.RegWriteout
  Memory.io.regwritein:=Execute.io.RegWriteout
//  val memWen = Reg(Bool())
//  memWen := Execute.io.MemWriteout
  Memory.io.Wen := Execute.io.MemWriteout
////
//  val wbselect2 = Reg(UInt(2.W))
//  wbselect2:= Execute.io.wbselectout
  Memory.io.wbselectin :=  Execute.io.wbselectout
//
//  val datain = Reg(UInt(32.W))//IN_B transfer
//  datain := Execute.io.in_Bout //in_b_out_of alu
  Memory.io.datain:=Execute.io.in_Bout
//
//  val lengthselc = Reg(UInt(2.W))
//  lengthselc := Execute.io.lengthselectout
  Memory.io.fun3 := Execute.io.lengthselectout
//
//  val readmem2 =  Reg(Bool())
//  readmem2 := Execute.io.readmemout
  Memory.io.readmem:=  Execute.io.readmemout
//
//  val RDin = Reg(UInt(5.W))
//  RDin := Execute.io.RDout
  Memory.io.RDin:=Execute.io.RDout
//
////
//  io.memout:= addr


  //  val dobranch = Reg(Bool())
  //  dobranch := Execute.io.doBranch
  //  Memory.io.d
//
  //MEM_WB
  //Memory.io <> Wb.io
//  val datamemout = Reg(UInt(32.W))
//  datamemout:= Memory.io.dmemdataout
  Wb.io.datamemin:=Memory.io.dmemdataout
//
//  val aludataout = Reg(UInt(32.W))
//  aludataout:= Memory.io.aludataout
  Wb.io.Aludatain:=Memory.io.aludataout
//
//  val pcout4 = Reg(UInt(32.W))
//  pcout4:= Memory.io.pcout
  Wb.io.pcin:=Memory.io.pcout
//
//  val wbselect3 = Reg(Bool())
//  wbselect3:= Memory.io.wbselectout
  Wb.io.wbselect := Memory.io.wbselectout
//
//  val Rd4 = Reg(UInt(5.W))
//  Rd4 := Memory.io.RDOut
  Wb.io.Rd:= Memory.io.RDOut
//
//  val Regwrite3 = Reg(Bool())
//  Regwrite3 := Memory.io.regwriteout
  Wb.io.Regwrite:= Memory.io.regwriteout
//
//
//  val pcselectin = Reg(Bool())
//  pcselectin := Memory.io.pcselecout
  Wb.io.pcselecin := Memory.io.pcselecout

//
//  //Wb to Dec
  Decode.io.RDin:= Wb.io.Rdout
  Decode.io.datain := Wb.io.dataOut
  Fetch.io.pcselect := Wb.io.pcselecout
  Fetch.io.aluout := Wb.io.dataOutpc
  Decode.io.RegWritein := Wb.io.Regwriteout

  io.wbout := Wb.io.dataOut



}