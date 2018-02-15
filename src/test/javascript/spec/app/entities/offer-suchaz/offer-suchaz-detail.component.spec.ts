/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { OfferSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/offer-suchaz/offer-suchaz-detail.component';
import { OfferSuchazService } from '../../../../../../main/webapp/app/entities/offer-suchaz/offer-suchaz.service';
import { OfferSuchaz } from '../../../../../../main/webapp/app/entities/offer-suchaz/offer-suchaz.model';

describe('Component Tests', () => {

    describe('OfferSuchaz Management Detail Component', () => {
        let comp: OfferSuchazDetailComponent;
        let fixture: ComponentFixture<OfferSuchazDetailComponent>;
        let service: OfferSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [OfferSuchazDetailComponent],
                providers: [
                    OfferSuchazService
                ]
            })
            .overrideTemplate(OfferSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OfferSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OfferSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new OfferSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.offer).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
