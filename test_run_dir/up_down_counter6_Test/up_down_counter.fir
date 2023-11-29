;buildInfoPackage: chisel3, version: 3.4.3, scalaVersion: 2.12.12, sbtVersion: 1.3.10
circuit up_down_counter : 
  module up_down_counter : 
    input clock : Clock
    input reset : UInt<1>
    output io : {out : UInt<4>, flip up_down : UInt<1>}
    
    reg counter : UInt<10>, clock with : (reset => (reset, UInt<10>("h00"))) @[task4.scala 11:24]
    reg flag : UInt, clock with : (reset => (reset, UInt<1>("h01"))) @[task4.scala 13:21]
    node _T = neq(counter, UInt<4>("h0a")) @[task4.scala 16:16]
    node _T_1 = eq(io.up_down, UInt<1>("h01")) @[task4.scala 16:45]
    node _T_2 = and(_T, _T_1) @[task4.scala 16:31]
    node _T_3 = eq(flag, UInt<1>("h01")) @[task4.scala 16:61]
    node _T_4 = and(_T_2, _T_3) @[task4.scala 16:53]
    when _T_4 : @[task4.scala 16:70]
      node _counter_T = add(counter, UInt<1>("h01")) @[task4.scala 17:24]
      node _counter_T_1 = tail(_counter_T, 1) @[task4.scala 17:24]
      counter <= _counter_T_1 @[task4.scala 17:13]
      skip @[task4.scala 16:70]
    else : @[task4.scala 18:38]
      node _T_5 = eq(counter, UInt<4>("h0a")) @[task4.scala 18:22]
      when _T_5 : @[task4.scala 18:38]
        node _counter_T_2 = sub(counter, UInt<1>("h01")) @[task4.scala 19:24]
        node _counter_T_3 = tail(_counter_T_2, 1) @[task4.scala 19:24]
        counter <= _counter_T_3 @[task4.scala 19:13]
        flag <= UInt<1>("h00") @[task4.scala 20:10]
        skip @[task4.scala 18:38]
      else : @[task4.scala 21:31]
        node _T_6 = eq(counter, UInt<1>("h00")) @[task4.scala 21:22]
        when _T_6 : @[task4.scala 21:31]
          flag <= UInt<1>("h01") @[task4.scala 22:10]
          skip @[task4.scala 21:31]
        else : @[task4.scala 23:28]
          node _T_7 = eq(flag, UInt<1>("h01")) @[task4.scala 23:19]
          when _T_7 : @[task4.scala 23:28]
            node _counter_T_4 = sub(counter, UInt<1>("h01")) @[task4.scala 24:24]
            node _counter_T_5 = tail(_counter_T_4, 1) @[task4.scala 24:24]
            counter <= _counter_T_5 @[task4.scala 24:13]
            skip @[task4.scala 23:28]
    io.out <= counter @[task4.scala 26:10]
    
