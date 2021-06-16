import {render} from "@testing-library/react";
import {AnimalProfile} from "../index";

it("test employee button", () =>  {
    const animal = {
        idAnimal: null,
        name: null,
        arrivalDate: null,
        description: null,
        breed: {
            name: null
        },
        size: {
            name: null
        }
    };
    const {getByTestId} = render(<AnimalProfile role={"EMPLOYEE"} animal={animal}/>)
    const employeeButton = getByTestId("employeeButton");
    expect(employeeButton).toBeInTheDocument();
})

it("test profile info", () =>  {
    const animal = {
        idAnimal: 1,
        name: "Wacław",
        arrivalDate: "2015-03-14",
        description: "Opis",
        breed: {
            name: "Mieszaniec"
        },
        size: {
            name: "Mały"
        }
    };
    const {getByTestId} = render(<AnimalProfile role={"EMPLOYEE"} animal={animal}/>)
    const container = getByTestId("profileContainer");
    expect(container).toHaveTextContent("Wacław")
    expect(container).toHaveTextContent("2015-03-14")
    expect(container).toHaveTextContent("Opis")
    expect(container).toHaveTextContent("Mieszaniec")
    expect(container).toHaveTextContent("Mały")
})

