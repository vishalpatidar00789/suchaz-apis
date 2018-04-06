/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { ConsumerProfileHistorySuchazDetailComponent } from '../../../../../../main/webapp/app/entities/consumer-profile-history-suchaz/consumer-profile-history-suchaz-detail.component';
import { ConsumerProfileHistorySuchazService } from '../../../../../../main/webapp/app/entities/consumer-profile-history-suchaz/consumer-profile-history-suchaz.service';
import { ConsumerProfileHistorySuchaz } from '../../../../../../main/webapp/app/entities/consumer-profile-history-suchaz/consumer-profile-history-suchaz.model';

describe('Component Tests', () => {

    describe('ConsumerProfileHistorySuchaz Management Detail Component', () => {
        let comp: ConsumerProfileHistorySuchazDetailComponent;
        let fixture: ComponentFixture<ConsumerProfileHistorySuchazDetailComponent>;
        let service: ConsumerProfileHistorySuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ConsumerProfileHistorySuchazDetailComponent],
                providers: [
                    ConsumerProfileHistorySuchazService
                ]
            })
            .overrideTemplate(ConsumerProfileHistorySuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConsumerProfileHistorySuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConsumerProfileHistorySuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ConsumerProfileHistorySuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.consumerProfileHistory).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
