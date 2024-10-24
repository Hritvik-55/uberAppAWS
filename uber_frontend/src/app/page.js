import * as actions from "@/actions/index.js";
import Image from "next/image";
import Link from "next/link";
import Taxi from "../../public/images/taxi-home-bg.jpg"
import "./globals.css"
export default function Home() {
  return (
    <div className=" container w-screen h-screen flex items-center justify-center relative">
    <Image className=" min-h-screen min-w-full max-w-full max-h-screen absolute top-0 left-0
     bg-slate-300 -z-10 bg-auto bg-no-repeat bg-center signupBG" alt="Taxi Sign" src={Taxi}  />
      <form action={actions.SignUpAction} className="opacity-50 hover:opacity-100 transition ease-in-out duration-500 signupForm">
      <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto lg:py-0">
        <div className="w-full bg-white rounded-lg shadow border md:mt-0 sm:max-w-md xl:p-0">
          <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
            <p className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
              Create an account
            
            
              </p><div>
                <label className="block mb-2 text-sm font-medium text-gray-900" htmlFor="name">
                  Your username
                </label>
                <input placeholder="Username" type="text" id="name" name="name" required className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg block w-full p-2.5" id="username" type="text"/>
              </div>
              <div>
                <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900">
                  Password
                </label>
                <input type="password" id="password" name="password" required className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg block w-full p-2.5" placeholder="••••••••" id="password" type="password"/>
              </div>
              <div>
                <label className="block mb-2 text-sm font-medium text-gray-900">
                  Email
                </label>
                <input className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg block w-full p-2.5" type="email" id="email" name="email" required placeholder="person@whatever.com" />
              </div>
              <div className="flex items-start">
                <div className="flex items-center h-5">
                  <input className="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-primary-300 bg-gray-700 border-gray-600 focus:ring-primary-600 ring-offset-gray-800" type="checkbox" aria-describedby="terms" id="terms"/>
                </div>
                <div className="ml-3 text-sm">
                  <label className="font-light text-gray-500 text-gray-300">
                    I accept the
                    <a href="#" className="font-medium text-primary-600 hover:underline text-primary-500">
                      Terms and Conditions
                    </a>
                  </label>
                </div>
              </div>

              <button className="w-full bg-yellow-500 hover:bg-yellow-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center  focus:ring-blue-800 text-white" type="submit">
                Create an account
              </button>
              <div className="w-full text-center">

              <Link href="/signin" className="hover:underline text-primary-300 font-medium text-gray-500 text-gray-300 mt-5">
                      Already Registered.
                    </Link>
              </div>
            
          </div>
        </div>
      </div></form>
    </div>
  );
}
