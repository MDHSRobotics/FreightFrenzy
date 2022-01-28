package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Autonomous extends LinearOpMode {
  private DcMotor FR;
  private DcMotor FL;
  private DcMotor BL;
  private DcMotor BR;
  private DcMotor vert;
  private DcMotor rot;
  
  private Servo claw;
  private Servo duckie;
  
  private DistanceSensor frontsensor;
  private DistanceSensor backsensor;
  private VoltageSensor ControlHub_VoltageSensor;
  
  
  @Override
  public void runOpMode() {
    double motorpos;
    double newmotorpos;
    double drivepos;
    double voltage;
    int pos = 2;
    int level = 0;
    
    frontsensor = hardwareMap.get(DistanceSensor.class, "frontsensor");
    backsensor = hardwareMap.get(DistanceSensor.class, "backsensor");
    FR = hardwareMap.get(DcMotor.class, "FR");
    FL = hardwareMap.get(DcMotor.class, "FL");
    BL = hardwareMap.get(DcMotor.class, "BL");
    BR = hardwareMap.get(DcMotor.class, "BR");
    vert = hardwareMap.get(DcMotor.class, "vert");
    rot = hardwareMap.get(DcMotor.class, "rot");
    duckie = hardwareMap.get(Servo.class, "duckie");
    claw = hardwareMap.get(Servo.class, "claw");
    ControlHub_VoltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");
    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here
      voltage = ControlHub_VoltageSensor.getVoltage();
      motorpos = rot.getCurrentPosition();
      newmotorpos = motorpos - 470;
      
      
      if (pos == 1){
        //forward(200);
        while(backsensor.getDistance(DistanceUnit.CM) < 13 && opModeIsActive()){
          forwardd();
        }
        stopmove();
        sleep(100);
        right(540);
        sleep(100);
        backd();
        sleep(1150);
        BL.setPower(-0.1);
        BR.setPower(0.1);
        FL.setPower(-0.1);
        FR.setPower(0.1);
        ducksgo();
        sleep(6000);
        ducksstop();
        stopmove();
        sleep(100);
        forward(260);
        stopmove();
        sleep(100);
        left(540);
        sleep(100);
        forward(120);
        sleep(400);
        telemetry.addData("dist", backsensor.getDistance(DistanceUnit.CM));
        telemetry.update();
        if(frontsensor.getDistance(DistanceUnit.CM) < 50){
          level = 1;
        }
        telemetry.addData("d1", frontsensor.getDistance(DistanceUnit.CM));
        sleep(200);
        straferight(300);
        sleep(200);
        if(frontsensor.getDistance(DistanceUnit.CM) < 50){
          level = 2;
        }
        telemetry.addData("d1", frontsensor.getDistance(DistanceUnit.CM));
        sleep(200);
        straferight(300);
        sleep(200);
        if(frontsensor.getDistance(DistanceUnit.CM) < 50){
          level = 3;
        }
        telemetry.addData("d1", frontsensor.getDistance(DistanceUnit.CM));
        telemetry.addData("level", level);
        telemetry.update();
        sleep(200);
        straferight(480);
        
        if(level == 1){
          straferight(240);
          sleep(100);
          rightpos(newmotorpos);
          sleep(500);
          forward(80);
          sleep(200);
          claw.setPosition(1);
          sleep(2000);
          claw.setPosition(0.5);
          back(100);
          leftpos(motorpos);
          left(480);
          forward(900);
          left(170);
          forward(500);
        
        }else if(level == 2){
          l2();
          sleep(200);
          straferight(250);
          sleep(100);
          rightpos(newmotorpos);
          sleep(500);
          forward(100);
          sleep(200);
          claw.setPosition(1);
          sleep(2000);
          claw.setPosition(0.5);
          back(250);
          leftpos(motorpos);
          left(480);
          forward(1200);
          left(170);
          forward(360);
          sleep(100);
          straferight(500);
          
        
        }else if (level == 3){
          l3();
          sleep(200);
          straferight(250);
          sleep(100);
          rightpos(newmotorpos);
          sleep(500);
          forward(150);
          sleep(200);
          claw.setPosition(1);
          sleep(2000);
          claw.setPosition(0.5);
          back(300);
          leftpos(motorpos);
          left(480);
          forward(1200);
          left(170);
          forward(290);
          sleep(100);
          straferight(70);
        }else{
          straferight(240);
          sleep(100);
          rightpos(newmotorpos);
          sleep(500);
          forward(80);
          sleep(200);
          claw.setPosition(1);
          sleep(2000);
          claw.setPosition(0.5);
          back(100);
          leftpos(motorpos);
          left(480);
          forward(900);
          left(170);
          forward(50);
        }
      
        
      }else if(pos == 4){
        forward(100);
        sleep(100);
        straferight(796);
        duckie.setPosition(1);
        sleep(7000);
        ducksstop();
        strafeleft(660);
        right(10);
        sleep(100);
        forward(165);
        sleep(400);
        
        if(frontsensor.getDistance(DistanceUnit.CM) < 50){
          level = 3;
        }
        sleep(200);
        strafeleft(300);
        sleep(200);
        if(frontsensor.getDistance(DistanceUnit.CM) < 50){
          level = 2;
        }
        sleep(200);
        strafeleft(300);
        sleep(200);
        if(frontsensor.getDistance(DistanceUnit.CM) < 50){
          level = 1;
        }
        telemetry.addData("level", level);
        telemetry.update();
        sleep(200);
        strafeleft(480);
       if(level == 1){
          strafeleft(200);
          sleep(100);
          rightpos(newmotorpos);
          sleep(500);
          forward(80);
          sleep(200);
          claw.setPosition(1);
          sleep(2000);
          claw.setPosition(0.5);
          back(100);
          leftpos(motorpos);
          right(700);
          forward(400);
          left(170);
          forward(500);
        }else if(level == 2){
          strafeleft(200);
          sleep(100);
          l2();
          sleep(200);
          rightpos(newmotorpos);
          sleep(500);
          forward(80);
          sleep(200);
          claw.setPosition(1);
          sleep(2000);
          claw.setPosition(0.5);
          back(100);
          leftpos(motorpos);
          right(700);
          forward(400);
          left(170);
          forward(700);
        }else{
          strafeleft(200);
          sleep(100);
          rightpos(newmotorpos);
          sleep(500);
          forward(80);
          sleep(200);
          claw.setPosition(1);
          sleep(2000);
          claw.setPosition(0.5);
          back(100);
          leftpos(motorpos);
          right(700);
          forward(400);
          left(170);
          forward(700);
        }
      }else if(pos == 2){
        forward(270);
        sleep(100);
        straferight(150);
        if(frontsensor.getDistance(DistanceUnit.CM) < 50){
          level = 2;
        }
        sleep(200);
        strafeleft(300);
        sleep(200);
        if(frontsensor.getDistance(DistanceUnit.CM) < 50){
          level = 1;
        }
        if(level == 0){
          level = 3;
        }
        
      }
      
      
      
      
      
      while (opModeIsActive()) {
        telemetry.addData("Front", frontsensor.getDistance(DistanceUnit.CM));
        telemetry.addData("Back", backsensor.getDistance(DistanceUnit.CM));
        telemetry.addData("level", level);
        telemetry.addData("key", ControlHub_VoltageSensor.getVoltage());
        telemetry.update();
      }
    }
  }
  //Movement
  private void forward(long t) {
    BL.setPower(0.5);
    BR.setPower(-0.5);
    FL.setPower(0.5);
    FR.setPower(-0.5);
    sleep(t);
    stopmove();
  }
  private void forwardd() {
    BL.setPower(0.5);
    BR.setPower(-0.5);
    FL.setPower(0.5);
    FR.setPower(-0.5);
  }
  private void back(long t) {
    BL.setPower(-0.5);
    BR.setPower(0.5);
    FL.setPower(-0.5);
    FR.setPower(0.5);
    sleep(t);
    stopmove();
  }
  private void backd() {
    BL.setPower(-0.3);
    BR.setPower(0.3);
    FL.setPower(-0.3);
    FR.setPower(0.3);
  }
  private void left(long t) {
    BL.setPower(-0.5);
    BR.setPower(-0.5);
    FL.setPower(-0.5);
    FR.setPower(-0.5);
    sleep(t);
    stopmove();
  }
  private void right(long t) {
    BL.setPower(0.5);
    BR.setPower(0.5);
    FL.setPower(0.5);
    FR.setPower(0.5);
    sleep(t);
    stopmove();
  }
  private void strafeleft(long t) {
    BL.setPower(-0.5);
    BR.setPower(0.5);
    FL.setPower(0.5);
    FR.setPower(-0.5);
    sleep(t);
    stopmove();
    
  }
  private void straferight(long t) {
    BL.setPower(0.5);
    BR.setPower(-0.5);
    FL.setPower(-0.5);
    FR.setPower(0.5);
    sleep(t);
    stopmove();
  }
  private void stopmove() {
    BL.setPower(0);
    BR.setPower(0);
    FL.setPower(0);
    FR.setPower(0);
  }
  //Ducks
  private void ducksgo() {
    duckie.setPosition(0);
  }
  private void ducksstop(){
    duckie.setPosition(0.5);
  }
  //Vertical Motion
  private void up(){
    vert.setPower(-0.5);
  }
  private void down(){
    vert.setPower(0.5);
  }
  private void stopvert(){
    vert.setPower(0);
  }
  private void l2(){
    up();
    sleep(600);
    stopvert();
  }
  private void l3(){
    up();
    sleep(3200);
    stopvert();
  }
  //Rotation
  private void rightpos(double nmp){
    while(rot.getCurrentPosition() > nmp && opModeIsActive()){
      rot.setPower(-0.2);
    }
    rot.setPower(0);
  }
    private void leftpos(double nmp){
    while(rot.getCurrentPosition() < nmp && opModeIsActive()){
      rot.setPower(0.2);
    }
    rot.setPower(0);
  }
  
}