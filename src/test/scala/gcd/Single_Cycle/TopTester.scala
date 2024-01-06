package Single_Cycle
import chisel3._
import org.scalatest._
import chiseltest._

class TopTester extends FreeSpec with ChiselScalatestTester {
  "2 stage test" in {
    test(new CORETOP) { c =>
      c.clock.step(200)
      c.io.out.expect(0.U)

    }
  }
}