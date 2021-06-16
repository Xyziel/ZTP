import {render} from "@testing-library/react";
import {NavigationBar} from "../index";

it('test button text', () => {
    const {getByTestId} = render(<NavigationBar role={"UNLOGGED"} isLoggedIn={true}/>)
    const button = getByTestId("loginButton")
    expect(button.innerHTML).toBe("Wyloguj")
})

it('test profile button if user logged in', () => {
    const {getByTestId} = render(<NavigationBar role={"USER"} isLoggedIn={true}/>)
    const button = getByTestId("profileButton")
    const navbar = getByTestId("navbar")
    expect(navbar).toContainElement(button)
})