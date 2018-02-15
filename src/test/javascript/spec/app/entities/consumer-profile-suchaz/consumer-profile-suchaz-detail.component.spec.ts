/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { ConsumerProfileSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/consumer-profile-suchaz/consumer-profile-suchaz-detail.component';
import { ConsumerProfileSuchazService } from '../../../../../../main/webapp/app/entities/consumer-profile-suchaz/consumer-profile-suchaz.service';
import { ConsumerProfileSuchaz } from '../../../../../../main/webapp/app/entities/consumer-profile-suchaz/consumer-profile-suchaz.model';

describe('Component Tests', () => {

    describe('ConsumerProfileSuchaz Management Detail Component', () => {
        let comp: ConsumerProfileSuchazDetailComponent;
        let fixture: ComponentFixture<ConsumerProfileSuchazDetailComponent>;
        let service: ConsumerProfileSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ConsumerProfileSuchazDetailComponent],
                providers: [
                    ConsumerProfileSuchazService
                ]
            })
            .overrideTemplate(ConsumerProfileSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConsumerProfileSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConsumerProfileSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ConsumerProfileSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.consumerProfile).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
