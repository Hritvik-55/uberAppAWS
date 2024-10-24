'use server'

import { redirect } from "next/navigation";
import { NextResponse } from "next/server";

export  async function SignUpAction(formData){

    const name= formData.get('name');
    const password=  formData.get('password');
    const email=  formData.get('email');



    const res = await fetch(`http://localhost:8080/auth/signUp`,{
        method:`POST`,
        headers:{
            'Content-Type':'application/json'
        },
        body:JSON.stringify({
            name,
            email,
            password,
        })
    })

    const data = await res.json();
    redirect(`/signin`)
    console.log(data)
    // return NextResponse.json({
    //     status:200,
    //     message:'user added'
    // });

}

export async function LogInAction(formData){

    const password=  formData.get('password');
    const email=  formData.get('email');



    const res = await fetch(`http://localhost:8080/auth/login`,{
        method:`POST`,
        headers:{
            'Content-Type':'application/json'
        },
        body:JSON.stringify({
            email,
            password,
        })
    })
    console.log(res)
    const data = await res.json();
    redirect(`/home`)
    console.log(data)
    // return NextResponse.json({
    //     status:200,
    //     message:'user added'
    // });

}