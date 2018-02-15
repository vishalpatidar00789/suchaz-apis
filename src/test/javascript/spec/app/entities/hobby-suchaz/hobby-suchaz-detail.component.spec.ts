/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { HobbySuchazDetailComponent } from '../../../../../../main/webapp/app/entities/hobby-suchaz/hobby-suchaz-detail.component';
import { HobbySuchazService } from '../../../../../../main/webapp/app/entities/hobby-suchaz/hobby-suchaz.service';
import { HobbySuchaz } from '../../../../../../main/webapp/app/entities/hobby-suchaz/hobby-suchaz.model';

describe('Component Tests', () => {

    describe('HobbySuchaz Management Detail Component', () => {
        let comp: HobbySuchazDetailComponent;
        let fixture: ComponentFixture<HobbySuchazDetailComponent>;
        let service: HobbySuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [HobbySuchazDetailComponent],
                providers: [
                    HobbySuchazService
                ]
            })
            .overrideTemplate(HobbySuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(HobbySuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HobbySuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new HobbySuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.hobby).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
