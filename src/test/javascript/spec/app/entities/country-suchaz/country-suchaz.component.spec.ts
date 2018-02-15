/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { CountrySuchazComponent } from '../../../../../../main/webapp/app/entities/country-suchaz/country-suchaz.component';
import { CountrySuchazService } from '../../../../../../main/webapp/app/entities/country-suchaz/country-suchaz.service';
import { CountrySuchaz } from '../../../../../../main/webapp/app/entities/country-suchaz/country-suchaz.model';

describe('Component Tests', () => {

    describe('CountrySuchaz Management Component', () => {
        let comp: CountrySuchazComponent;
        let fixture: ComponentFixture<CountrySuchazComponent>;
        let service: CountrySuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [CountrySuchazComponent],
                providers: [
                    CountrySuchazService
                ]
            })
            .overrideTemplate(CountrySuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CountrySuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CountrySuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new CountrySuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.countries[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
