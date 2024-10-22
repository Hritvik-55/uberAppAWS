import * as actions from "@/actions/index.js";

export default function SignInPage() {
  return (
    <div className=" container w-screen h-screen flex items-center justify-center">
    <form action={actions.LogInAction} className="px-5 py-8 bg-gray-600 flex flex-col justify-between w-1/4 h-1/3">
    <div className="flex justify-between">

<label htmlFor="email">Email:</label>
<input type="email" id="email" name="email" required />
</div>
    <div className="flex justify-between">

      <label htmlFor="password">Password:</label>
      <input type="password" id="password" name="password" required />
</div>


    <div className="flex justify-center gap-2">

      <button type="submit">Submit</button>
</div>
    </form>
  </div>
  )
}
