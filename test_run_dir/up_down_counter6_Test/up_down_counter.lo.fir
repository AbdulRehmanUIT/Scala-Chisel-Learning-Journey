circuit up_down_counter :
  module up_down_counter :
    input clock : Clock
    input reset : UInt<1>
    output io_out : UInt<4>
    input io_up_down : UInt<1>

    reg counter : UInt<10>, clock with :
      reset => (UInt<1>("h0"), counter) @[task4.scala 11:24]
    reg flag : UInt<1>, clock with :
      reset => (UInt<1>("h0"), flag) @[task4.scala 13:21]
    node _T = neq(counter, UInt<4>("ha")) @[task4.scala 16:16]
    node _T_1 = eq(io_up_down, UInt<1>("h1")) @[task4.scala 16:45]
    node _T_2 = and(_T, _T_1) @[task4.scala 16:31]
    node _T_3 = eq(flag, UInt<1>("h1")) @[task4.scala 16:61]
    node _T_4 = and(_T_2, _T_3) @[task4.scala 16:53]
    node _counter_T = add(counter, UInt<1>("h1")) @[task4.scala 17:24]
    node _counter_T_1 = tail(_counter_T, 1) @[task4.scala 17:24]
    node _T_5 = eq(counter, UInt<4>("ha")) @[task4.scala 18:22]
    node _counter_T_2 = sub(counter, UInt<1>("h1")) @[task4.scala 19:24]
    node _counter_T_3 = tail(_counter_T_2, 1) @[task4.scala 19:24]
    node _T_6 = eq(counter, UInt<1>("h0")) @[task4.scala 21:22]
    node _T_7 = eq(flag, UInt<1>("h1")) @[task4.scala 23:19]
    node _counter_T_4 = sub(counter, UInt<1>("h1")) @[task4.scala 24:24]
    node _counter_T_5 = tail(_counter_T_4, 1) @[task4.scala 24:24]
    node _GEN_0 = mux(_T_7, _counter_T_5, counter) @[task4.scala 23:28 task4.scala 24:13 task4.scala 11:24]
    node _GEN_1 = mux(_T_6, UInt<1>("h1"), flag) @[task4.scala 21:31 task4.scala 22:10 task4.scala 13:21]
    node _GEN_2 = mux(_T_6, counter, _GEN_0) @[task4.scala 21:31 task4.scala 11:24]
    node _GEN_3 = mux(_T_5, _counter_T_3, _GEN_2) @[task4.scala 18:38 task4.scala 19:13]
    node _GEN_4 = mux(_T_5, UInt<1>("h0"), _GEN_1) @[task4.scala 18:38 task4.scala 20:10]
    node _GEN_5 = mux(_T_4, _counter_T_1, _GEN_3) @[task4.scala 16:70 task4.scala 17:13]
    node _GEN_6 = mux(_T_4, flag, _GEN_4) @[task4.scala 16:70 task4.scala 13:21]
    io_out <= bits(counter, 3, 0) @[task4.scala 26:10]
    counter <= mux(reset, UInt<10>("h0"), _GEN_5) @[task4.scala 11:24 task4.scala 11:24]
    flag <= mux(reset, UInt<1>("h1"), _GEN_6) @[task4.scala 13:21 task4.scala 13:21]
