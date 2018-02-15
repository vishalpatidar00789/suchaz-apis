/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { CountrySuchazDetailComponent } from '../../../../../../main/webapp/app/entities/country-suchaz/country-suchaz-detail.component';
import { CountrySuchazService } from '../../../../../../main/webapp/app/entities/country-suchaz/country-suchaz.service';
import { CountrySuchaz } from '../../../../../../main/webapp/app/entities/country-suchaz/country-suchaz.model';

describe('Component Tests', () => {

    describe('CountrySuchaz Management Detail Component', () => {
        let comp: CountrySuchazDetailComponent;
        let fixture: ComponentFixture<CountrySuchazDetailComponent>;
        let service: CountrySuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [CountrySuchazDetailComponent],
                providers: [
                    CountrySuchazService
                ]
            })
            .overrideTemplate(CountrySuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CountrySuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CountrySuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new CountrySuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.country).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
