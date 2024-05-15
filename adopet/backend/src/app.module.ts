import { Module } from '@nestjs/common';

import { AuthModule } from './auth/auth.module';
import { ConfigModule } from '@nestjs/config';
import { PrismaModule } from './prisma/prisma.module';
import { UserController } from './user/user.controller';
import { UserService } from './user/user.service';
import { UserModule } from './user/user.module';
import { PetModule } from './pet/pet.module';

@Module({
  imports: [AuthModule, PrismaModule,ConfigModule.forRoot({
    isGlobal:true,
  }), UserModule, PetModule],
  controllers: [UserController],
  providers: [UserService],
})
export class AppModule {}
