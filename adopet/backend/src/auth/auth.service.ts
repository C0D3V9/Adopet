import { ForbiddenException, Injectable } from "@nestjs/common";

import { PrismaService } from "src/prisma/prisma.service";
import { DtoAuth } from "./dto";
import * as argon2 from 'argon2';
import { PrismaClientKnownRequestError } from "@prisma/client/runtime/library";
import { JwtService } from "@nestjs/jwt";
import { ConfigService } from "@nestjs/config";

@Injectable()
export class AuthService {
    constructor(private prisma:PrismaService,private jwt:JwtService,private config:ConfigService){}
    async signin(dto:DtoAuth){
        const hash=await argon2.hash(dto.password);
       try {
        const user=await this.prisma.user.create({
            data:
            {email:dto.email,
                password:hash,
                name:dto.name
                
            }
           });
           delete dto.password;
            return user;
       } catch (error) {
        if(error instanceof PrismaClientKnownRequestError){
            if(error.code==='P2002'){
                throw new ForbiddenException("the email is repeted")
            }
        }throw error;
       }
    }



    async login(dto:DtoAuth){
        const user=await this.prisma.user.findUnique({
            where:{
                email:dto.email,
            }
        });
        if(!user){
            throw new ForbiddenException("the email isn't valid");
        }
        const matchps=await argon2.verify(user.password,dto.password);
        if(!matchps){
            throw new ForbiddenException("the password is not correct");
        }
        delete dto.password;
        return this.jwtsing(user.email,user.id);
    }
    async jwtsing(email:string,id:number){
        const parameters={
            email,
            userid:id
        };
        const secertfile=this.config.get("JWT_PASSWORD");
        const token=await this.jwt.signAsync(parameters,{
            expiresIn:"15m",
            secret:secertfile,
            
        });
        return {token}
    }


}