
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "DriveBaseOp2021", group = "")
public class DriveBaseOp2021 extends LinearOpMode {

  private DcMotor FR;
  private DcMotor FL;
  private DcMotor BL;
  private DcMotor BR;
  //private DcMotor duckie;
  private DcMotor rot;
  private DcMotor vert;
  private DcMotor intake;
  private Servo claw;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    float drivey;
    float drivex;
    double right;
    double left;
    float pos;

    FR = hardwareMap.get(DcMotor.class, "FR");
    FL = hardwareMap.get(DcMotor.class, "FL");
    BL = hardwareMap.get(DcMotor.class, "BL");
    BR = hardwareMap.get(DcMotor.class, "BR");
    //duckie = hardwareMap.get(DcMotor.class, "duckie");
    rot = hardwareMap.get(DcMotor.class, "rot");
    vert = hardwareMap.get(DcMotor.class, "vert");
    claw = hardwareMap.get(Servo.class, "claw");
    intake = hardwareMap.get(DcMotor.class, "intake");

    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Drive
        drivey = gamepad1.left_stick_y;
        drivex = gamepad1.left_stick_x;
        right = drivey + drivex;
        left = drivex - drivey;
        FR.setPower(right * 0.5);
        FL.setPower(left * 0.5);
        BL.setPower(left * 0.5);
        BR.setPower(right * 0.5);
        //Stafe
        if (gamepad1.left_bumper) {
          BL.setPower(-1);
          BR.setPower(1);
          FL.setPower(1);
          FR.setPower(-1);
        } else if (gamepad1.right_bumper) {
          BL.setPower(1);
          BR.setPower(-1);
          FL.setPower(-1);
          FR.setPower(1);
        }
        //duckie
        //if(gamepad2.right_trigger > 0){
        //  duckie.setPower(1);
        //}else if (gamepad2.left_trigger > 0){
        //  duckie.setPower(-1);
        //}else{
        //  duckie.setPower(0);
        //}
        //rotation
        pos = rot.getCurrentPosition();
        rot.setPower(-gamepad2.left_stick_x * 0.5);
        //vertical
        vert.setPower(gamepad2.left_stick_y);
        //claw
        if(gamepad2.x){
          claw.setPosition(1);
        }else if(gamepad2.b){
          claw.setPosition(-1);
          
        }
        if(gamepad1.right_trigger > 0){
          intake.setPower(1);
        }else if (gamepad1.left_trigger > 0){
          intake.setPower(-1);
        }else{
          intake.setPower(0);
        }
        
        
        telemetry.addData("motor", rot.getCurrentPosition());
        telemetry.update();
      }
    }
  }
  

  
  
  
}