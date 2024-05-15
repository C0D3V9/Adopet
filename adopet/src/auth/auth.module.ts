import { Module } from '@nestjs/common';
import { AuthService } from './auth.service';
import { AuthController } from './auth.controller';
import { JwtModule } from '@nestjs/jwt';
import { JwtStrategy } from './startegy';


@Module({
  providers: [AuthService,JwtStrategy],
  imports:[JwtModule.register({})],
  controllers: [AuthController]
})
export class AuthModule {}
