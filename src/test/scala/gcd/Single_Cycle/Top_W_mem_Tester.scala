package Single_Cycle
import chisel3._
import org.scalatest._
import chiseltest._

class Top_W_mem_Tester extends FreeSpec with ChiselScalatestTester {
  "DataPath out Test" in {
    test(new Top_W_mem) { c =>
      c.clock.step(8)
      c.io.out.expect(0.U)

    }
  }
}