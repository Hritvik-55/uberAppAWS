import * as actions from "@/actions/index.js";

export default function Home() {
  return (
    <div className=" container w-screen h-screen flex items-center justify-center">
      <form action={actions.SignUpAction} className="px-5 py-8 bg-gray-600 flex flex-col justify-between w-1/4 h-1/3">
      <div className="flex justify-between">
        <label htmlFor="name">Name:</label>
        <input type="text" id="name" name="name" required />
      </div>
      <div className="flex justify-between">

        <label htmlFor="password">Password:</label>
        <input type="password" id="password" name="password" required />
</div>
      <div className="flex justify-between">

        <label htmlFor="email">Email:</label>
        <input type="email" id="email" name="email" required />
</div>

      <div className="flex justify-center gap-2">

        <button type="submit">Submit</button>
</div>
      </form>
    </div>
  );
}
