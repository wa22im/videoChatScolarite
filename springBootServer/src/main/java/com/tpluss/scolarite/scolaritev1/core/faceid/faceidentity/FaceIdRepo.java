package com.tpluss.scolarite.scolaritev1.core.faceid.faceidentity;

import com.tpluss.scolarite.scolaritev1.communmodel.predictdata.Getpredictdata;
import com.tpluss.scolarite.scolaritev1.core.home.predictcontroller.models.ResultFaceDest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.List;


@Repository
public interface FaceIdRepo extends JpaRepository<FaceId, Long> {


  @Query(value = "select id ,  sqrt(power(e0 - (:#{#e.get(0)}),2)+power(e1 - (:#{#e.get(1)}),2)+power(e2 - (:#{#e.get(2)}),2)+power(e3 - (:#{#e.get(3)}),2)+power(e4 - (:#{#e.get(4)}),2)+power(e5 - (:#{#e.get(5)}),2)+power(e6 - (:#{#e.get(6)}),2)+power(e7 - (:#{#e.get(7)}),2)+power(e8 - (:#{#e.get(8)}),2)+power(e9 - (:#{#e.get(9)}),2)+power(e10 - (:#{#e.get(10)}),2)+power(e11 - (:#{#e.get(11)}),2)+power(e12 - (:#{#e.get(12)}),2)+power(e13 - (:#{#e.get(13)}),2)+power(e14 - (:#{#e.get(14)}),2)+power(e15 - (:#{#e.get(15)}),2)+power(e16 - (:#{#e.get(16)}),2)+power(e17 - (:#{#e.get(17)}),2)+power(e18 - (:#{#e.get(18)}),2)+power(e19 - (:#{#e.get(19)}),2)+power(e20 - (:#{#e.get(20)}),2)+power(e21 - (:#{#e.get(21)}),2)+power(e22 - (:#{#e.get(22)}),2)+power(e23 - (:#{#e.get(23)}),2)+power(e24 - (:#{#e.get(24)}),2)+power(e25 - (:#{#e.get(25)}),2)+power(e26 - (:#{#e.get(26)}),2)+power(e27 - (:#{#e.get(27)}),2)+power(e28 - (:#{#e.get(28)}),2)+power(e29 - (:#{#e.get(29)}),2)+power(e30 - (:#{#e.get(30)}),2)+power(e31 - (:#{#e.get(31)}),2)+power(e32 - (:#{#e.get(32)}),2)+power(e33 - (:#{#e.get(33)}),2)+power(e34 - (:#{#e.get(34)}),2)+power(e35 - (:#{#e.get(35)}),2)+power(e36 - (:#{#e.get(36)}),2)+power(e37 - (:#{#e.get(37)}),2)+power(e38 - (:#{#e.get(38)}),2)+power(e39 - (:#{#e.get(39)}),2)+power(e40 - (:#{#e.get(40)}),2)+power(e41 - (:#{#e.get(41)}),2)+power(e42 - (:#{#e.get(42)}),2)+power(e43 - (:#{#e.get(43)}),2)+power(e44 - (:#{#e.get(44)}),2)+power(e45 - (:#{#e.get(45)}),2)+power(e46 - (:#{#e.get(46)}),2)+power(e47 - (:#{#e.get(47)}),2)+power(e48 - (:#{#e.get(48)}),2)+power(e49 - (:#{#e.get(49)}),2)+power(e50 - (:#{#e.get(50)}),2)+power(e51 - (:#{#e.get(51)}),2)+power(e52 - (:#{#e.get(52)}),2)+power(e53 - (:#{#e.get(53)}),2)+power(e54 - (:#{#e.get(54)}),2)+power(e55 - (:#{#e.get(55)}),2)+power(e56 - (:#{#e.get(56)}),2)+power(e57 - (:#{#e.get(57)}),2)+power(e58 - (:#{#e.get(58)}),2)+power(e59 - (:#{#e.get(59)}),2)+power(e60 - (:#{#e.get(60)}),2)+power(e61 - (:#{#e.get(61)}),2)+power(e62 - (:#{#e.get(62)}),2)+power(e63 - (:#{#e.get(63)}),2)+power(e64 - (:#{#e.get(64)}),2)+power(e65 - (:#{#e.get(65)}),2)+power(e66 - (:#{#e.get(66)}),2)+power(e67 - (:#{#e.get(67)}),2)+power(e68 - (:#{#e.get(68)}),2)+power(e69 - (:#{#e.get(69)}),2)+power(e70 - (:#{#e.get(70)}),2)+power(e71 - (:#{#e.get(71)}),2)+power(e72 - (:#{#e.get(72)}),2)+power(e73 - (:#{#e.get(73)}),2)+power(e74 - (:#{#e.get(74)}),2)+power(e75 - (:#{#e.get(75)}),2)+power(e76 - (:#{#e.get(76)}),2)+power(e77 - (:#{#e.get(77)}),2)+power(e78 - (:#{#e.get(78)}),2)+power(e79 - (:#{#e.get(79)}),2)+power(e80 - (:#{#e.get(80)}),2)+power(e81 - (:#{#e.get(81)}),2)+power(e82 - (:#{#e.get(82)}),2)+power(e83 - (:#{#e.get(83)}),2)+power(e84 - (:#{#e.get(84)}),2)+power(e85 - (:#{#e.get(85)}),2)+power(e86 - (:#{#e.get(86)}),2)+power(e87 - (:#{#e.get(87)}),2)+power(e88 - (:#{#e.get(88)}),2)+power(e89 - (:#{#e.get(89)}),2)+power(e90 - (:#{#e.get(90)}),2)+power(e91 - (:#{#e.get(91)}),2)+power(e92 - (:#{#e.get(92)}),2)+power(e93 - (:#{#e.get(93)}),2)+power(e94 - (:#{#e.get(94)}),2)+power(e95 - (:#{#e.get(95)}),2)+power(e96 - (:#{#e.get(96)}),2)+power(e97 - (:#{#e.get(97)}),2)+power(e98 - (:#{#e.get(98)}),2)+power(e99 - (:#{#e.get(99)}),2)+power(e100 - (:#{#e.get(100)}),2)+power(e101 - (:#{#e.get(101)}),2)+power(e102 - (:#{#e.get(102)}),2)+power(e103 - (:#{#e.get(103)}),2)+power(e104 - (:#{#e.get(104)}),2)+power(e105 - (:#{#e.get(105)}),2)+power(e106 - (:#{#e.get(106)}),2)+power(e107 - (:#{#e.get(107)}),2)+power(e108 - (:#{#e.get(108)}),2)+power(e109 - (:#{#e.get(109)}),2)+power(e110 - (:#{#e.get(110)}),2)+power(e111 - (:#{#e.get(111)}),2)+power(e112 - (:#{#e.get(112)}),2)+power(e113 - (:#{#e.get(113)}),2)+power(e114 - (:#{#e.get(114)}),2)+power(e115 - (:#{#e.get(115)}),2)+power(e116 - (:#{#e.get(116)}),2)+power(e117 - (:#{#e.get(117)}),2)+power(e118 - (:#{#e.get(118)}),2)+power(e119 - (:#{#e.get(119)}),2)+power(e120 - (:#{#e.get(120)}),2)+power(e121 - (:#{#e.get(121)}),2)+power(e122 - (:#{#e.get(122)}),2)+power(e123 - (:#{#e.get(123)}),2)+power(e124 - (:#{#e.get(124)}),2)+power(e125 - (:#{#e.get(125)}),2)+power(e126 - (:#{#e.get(126)}),2)+power(e127 - (:#{#e.get(127)}),2)+power(e128 - (:#{#e.get(128)}),2)+power(e129 - (:#{#e.get(129)}),2)+power(e130 - (:#{#e.get(130)}),2)+power(e131 - (:#{#e.get(131)}),2)+power(e132 - (:#{#e.get(132)}),2)+power(e133 - (:#{#e.get(133)}),2)+power(e134 - (:#{#e.get(134)}),2)+power(e135 - (:#{#e.get(135)}),2)+power(e136 - (:#{#e.get(136)}),2)+power(e137 - (:#{#e.get(137)}),2)+power(e138 - (:#{#e.get(138)}),2)+power(e139 - (:#{#e.get(139)}),2)+power(e140 - (:#{#e.get(140)}),2)+power(e141 - (:#{#e.get(141)}),2)+power(e142 - (:#{#e.get(142)}),2)+power(e143 - (:#{#e.get(143)}),2)+power(e144 - (:#{#e.get(144)}),2)+power(e145 - (:#{#e.get(145)}),2)+power(e146 - (:#{#e.get(146)}),2)+power(e147 - (:#{#e.get(147)}),2)+power(e148 - (:#{#e.get(148)}),2)+power(e149 - (:#{#e.get(149)}),2)+power(e150 - (:#{#e.get(150)}),2)+power(e151 - (:#{#e.get(151)}),2)+power(e152 - (:#{#e.get(152)}),2)+power(e153 - (:#{#e.get(153)}),2)+power(e154 - (:#{#e.get(154)}),2)+power(e155 - (:#{#e.get(155)}),2)+power(e156 - (:#{#e.get(156)}),2)+power(e157 - (:#{#e.get(157)}),2)+power(e158 - (:#{#e.get(158)}),2)+power(e159 - (:#{#e.get(159)}),2)+power(e160 - (:#{#e.get(160)}),2)+power(e161 - (:#{#e.get(161)}),2)+power(e162 - (:#{#e.get(162)}),2)+power(e163 - (:#{#e.get(163)}),2)+power(e164 - (:#{#e.get(164)}),2)+power(e165 - (:#{#e.get(165)}),2)+power(e166 - (:#{#e.get(166)}),2)+power(e167 - (:#{#e.get(167)}),2)+power(e168 - (:#{#e.get(168)}),2)+power(e169 - (:#{#e.get(169)}),2)+power(e170 - (:#{#e.get(170)}),2)+power(e171 - (:#{#e.get(171)}),2)+power(e172 - (:#{#e.get(172)}),2)+power(e173 - (:#{#e.get(173)}),2)+power(e174 - (:#{#e.get(174)}),2)+power(e175 - (:#{#e.get(175)}),2)+power(e176 - (:#{#e.get(176)}),2)+power(e177 - (:#{#e.get(177)}),2)+power(e178 - (:#{#e.get(178)}),2)+power(e179 - (:#{#e.get(179)}),2)+power(e180 - (:#{#e.get(180)}),2)+power(e181 - (:#{#e.get(181)}),2)+power(e182 - (:#{#e.get(182)}),2)+power(e183 - (:#{#e.get(183)}),2)+power(e184 - (:#{#e.get(184)}),2)+power(e185 - (:#{#e.get(185)}),2)+power(e186 - (:#{#e.get(186)}),2)+power(e187 - (:#{#e.get(187)}),2)+power(e188 - (:#{#e.get(188)}),2)+power(e189 - (:#{#e.get(189)}),2)+power(e190 - (:#{#e.get(190)}),2)+power(e191 - (:#{#e.get(191)}),2)) as dist,student_id from face_id  having  dist  <= 1.0 ORDER BY dist ",nativeQuery = true)
//@Query(value = "select id , e0 from face_id  " ,nativeQuery = true)

  List <Object[]> findFacePrint ( @Param("e") ArrayList<Double> e) ;

}
