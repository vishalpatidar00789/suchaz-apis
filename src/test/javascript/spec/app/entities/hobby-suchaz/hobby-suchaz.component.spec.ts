/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { HobbySuchazComponent } from '../../../../../../main/webapp/app/entities/hobby-suchaz/hobby-suchaz.component';
import { HobbySuchazService } from '../../../../../../main/webapp/app/entities/hobby-suchaz/hobby-suchaz.service';
import { HobbySuchaz } from '../../../../../../main/webapp/app/entities/hobby-suchaz/hobby-suchaz.model';

describe('Component Tests', () => {

    describe('HobbySuchaz Management Component', () => {
        let comp: HobbySuchazComponent;
        let fixture: ComponentFixture<HobbySuchazComponent>;
        let service: HobbySuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [HobbySuchazComponent],
                providers: [
                    HobbySuchazService
                ]
            })
            .overrideTemplate(HobbySuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(HobbySuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HobbySuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new HobbySuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.hobbies[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
