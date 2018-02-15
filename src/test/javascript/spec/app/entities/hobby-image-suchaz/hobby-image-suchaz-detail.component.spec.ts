/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { HobbyImageSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/hobby-image-suchaz/hobby-image-suchaz-detail.component';
import { HobbyImageSuchazService } from '../../../../../../main/webapp/app/entities/hobby-image-suchaz/hobby-image-suchaz.service';
import { HobbyImageSuchaz } from '../../../../../../main/webapp/app/entities/hobby-image-suchaz/hobby-image-suchaz.model';

describe('Component Tests', () => {

    describe('HobbyImageSuchaz Management Detail Component', () => {
        let comp: HobbyImageSuchazDetailComponent;
        let fixture: ComponentFixture<HobbyImageSuchazDetailComponent>;
        let service: HobbyImageSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [HobbyImageSuchazDetailComponent],
                providers: [
                    HobbyImageSuchazService
                ]
            })
            .overrideTemplate(HobbyImageSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(HobbyImageSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HobbyImageSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new HobbyImageSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.hobbyImage).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
