import * as actions from "@/actions/index.js";
import Image from "next/image";
import Link from "next/link";
import Taxi from "../../../public/images/taxi-home-bg.jpg"
export default function SignInPage() {
  return (
    <div className=" container w-screen h-screen flex items-center justify-center">



    <Image className=" min-h-screen min-w-full max-w-full max-h-screen absolute top-0 left-0
     bg-slate-300 -z-10 bg-auto bg-no-repeat bg-center signupBG" alt="Taxi Sign" src={Taxi}  />
      <form action={actions.LogInAction} className="opacity-50 hover:opacity-100 transition ease-in-out duration-500 signupForm">
      <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto lg:py-0">
        <div className="w-full bg-white rounded-lg shadow border md:mt-0 sm:max-w-md xl:p-0">
          <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
            <p className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
              Welcome Back
            
            
              </p>
              <div>
                <label className="block mb-2 text-sm font-medium text-gray-900">
                  Email
                </label>
                <input className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg block w-full p-2.5" type="email" id="email" name="email" required placeholder="person@whatever.com" />
              </div>
              <div>
                <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900">
                  Password
                </label>
                <input type="password" id="password" name="password" required className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg block w-full p-2.5" placeholder="••••••••" id="password" type="password"/>
              </div>
            


              <button className="w-full bg-yellow-500 hover:bg-yellow-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center  focus:ring-blue-800 text-white" type="submit">
                Sign In
              </button>
              <div className="w-full text-center">

              <Link href="/" className="hover:underline text-primary-300 font-medium text-gray-500 text-gray-300 mt-5">
                      Haven't Registered Yet?
                    </Link>
              </div>
              </div>
            

          </div>
      </div></form>
  </div>
  )
}
