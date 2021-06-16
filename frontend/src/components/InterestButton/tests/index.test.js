import {render} from "@testing-library/react";
import {act} from "react-dom/test-utils";
import {InterestedButton} from "../index";

it("test button text for interested", () =>  {
    const {getByTestId} = render(<InterestedButton isInterested={true}/>)
    const button = getByTestId("button")
    expect(button.innerHTML).toBe("Nie jestem już<br>zainteresowany")
})

it("test button text for not interested", () => {
    const {getByTestId} =  render(<InterestedButton isInterested={false}/>)
    const button = getByTestId("button")
    expect(button.innerHTML).toBe("Jesteś zainteresowany?<br>Daj znać innym! Kliknij!")
})

it("test if function addInterestFunc clicked", () => {
    const addInterestFunc = jest.fn();
    const {getByTestId} = render(<InterestedButton isInterested={false} role="USER" isLoggedIn={true} addInterestFunc={addInterestFunc}/>);
    const button = getByTestId("button")
    act(() => {
        button.dispatchEvent(new MouseEvent("click", { bubbles: true }));
    });
    expect(addInterestFunc).toHaveBeenCalledTimes(1);

})

