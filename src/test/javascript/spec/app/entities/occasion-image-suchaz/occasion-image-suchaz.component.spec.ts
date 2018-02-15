/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { OccasionImageSuchazComponent } from '../../../../../../main/webapp/app/entities/occasion-image-suchaz/occasion-image-suchaz.component';
import { OccasionImageSuchazService } from '../../../../../../main/webapp/app/entities/occasion-image-suchaz/occasion-image-suchaz.service';
import { OccasionImageSuchaz } from '../../../../../../main/webapp/app/entities/occasion-image-suchaz/occasion-image-suchaz.model';

describe('Component Tests', () => {

    describe('OccasionImageSuchaz Management Component', () => {
        let comp: OccasionImageSuchazComponent;
        let fixture: ComponentFixture<OccasionImageSuchazComponent>;
        let service: OccasionImageSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [OccasionImageSuchazComponent],
                providers: [
                    OccasionImageSuchazService
                ]
            })
            .overrideTemplate(OccasionImageSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OccasionImageSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OccasionImageSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new OccasionImageSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.occasionImages[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
